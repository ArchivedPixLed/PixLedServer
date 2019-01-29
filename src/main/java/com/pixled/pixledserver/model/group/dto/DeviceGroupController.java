package com.pixled.pixledserver.model.group.dto;

import com.pixled.pixledserver.model.device.base.Device;
import com.pixled.pixledserver.model.device.base.dao.DeviceDao;
import com.pixled.pixledserver.model.device.base.dto.DeviceDto;
import com.pixled.pixledserver.model.device.base.dto.SimpleDeviceDto;
import com.pixled.pixledserver.model.group.DeviceGroup;
import com.pixled.pixledserver.model.group.dao.DeviceGroupDao;
import com.pixled.pixledserver.mqtt.MqttConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/groups")
@Transactional
public class DeviceGroupController {

    @Autowired
    DeviceDao deviceDao;
    @Autowired
    DeviceGroupDao deviceGroupDao;
    @Autowired
    private MqttConnection mqttConnection;

    @GetMapping
    public List<DeviceGroupDto> findAll() {
        return deviceGroupDao.findAll()
                .stream()
                .map(DeviceGroupDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public DeviceGroupDto findById(@PathVariable Integer id) {
        return deviceGroupDao.findById(id).map(room -> new DeviceGroupDto(room)).orElse(null);
    }

    @GetMapping(path = "/{id}/devices")
    public List<DeviceDto> findGroupDevices(@PathVariable Integer id) {
        return deviceDao.findByDeviceGroups(deviceGroupDao.findById(id).orElseThrow(IllegalArgumentException::new))
                .stream()
                .map(device -> new SimpleDeviceDto(device))
                .collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}/switch")
    public List<DeviceDto> switchGroup(@PathVariable Integer id) {
        DeviceGroup deviceGroup = deviceGroupDao.findById(id).orElseThrow(IllegalArgumentException::new);
        deviceGroup.switchGroup();
        deviceGroupDao.save(deviceGroup);
        ArrayList<DeviceDto> deviceDtos = new ArrayList<>();
        for (Device device : deviceGroup.getDevices()) {
            deviceDtos.add(new SimpleDeviceDto(device));
//            mqttConnection.publishSwitch(
//                    room.getBuilding().getId(),
//                    room.getId(),
//                    light.getId(),
//                    light.getStatus());
        }
        mqttConnection.publishGroupSwitch(deviceGroup.getId(), deviceGroup.getDeviceGroupState().getToggleState());
        return deviceDtos;
    }
//
//    @PostMapping
//    public DeviceGroupDto create(@RequestBody DeviceGroupDto dto) {
//        DeviceGroup room = null;
//        if (dto.getId() != null) {
//            room = roomDao.findById(dto.getId()).orElse(null);
//        }
//
//        if (room == null) {
//            room = roomDao.save(new DeviceGroup(dto.getName(), dto.getFloor(), buildingDao.getOne(dto.getBuildingId())));
//        } else {
//            roomDao.save(room);
//        }
//
//        return new DeviceGroupDto(room);
//    }
//
//    @DeleteMapping(path="/{id}")
//    public void delete(@PathVariable Long id) {
//        roomDao.deleteById(id);
//    }

}
