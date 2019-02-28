package com.pixled.pixledserver.model.group.dto;

import com.pixled.pixledserver.core.device.base.Device;
import com.pixled.pixledserver.core.device.base.DeviceDto;
import com.pixled.pixledserver.core.group.DeviceGroupDto;
import com.pixled.pixledserver.model.device.base.dao.DeviceDao;
import com.pixled.pixledserver.core.group.DeviceGroup;
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
        return deviceGroupDao.findById(id).map(device -> new DeviceGroupDto(device)).orElse(null);
    }

    @GetMapping(path = "/{id}/devices")
    public List<DeviceDto> findGroupDevices(@PathVariable Integer id) {
        return deviceDao.findByDeviceGroups(deviceGroupDao.findById(id).orElseThrow(IllegalArgumentException::new))
                .stream()
                .map(device -> device.generateDto())
                .collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}/switch")
    public List<DeviceDto> switchGroup(@PathVariable Integer id) {
        DeviceGroup deviceGroup = deviceGroupDao.findById(id).orElseThrow(IllegalArgumentException::new);
        deviceGroup.switchGroup();
        deviceGroupDao.save(deviceGroup);
        ArrayList<DeviceDto> deviceDtos = new ArrayList<>();
        for (Device device : deviceGroup.getDevices()) {
            deviceDtos.add(device.generateDto());
            if (mqttConnection.isConnected()) {
                mqttConnection.publishDeviceSwitch(
                        device.getId(),
                        device.getDeviceState().getToggleState());

            }
        }
        mqttConnection.publishGroupSwitch(deviceGroup.getId(), deviceGroup.getDeviceGroupState().getToggleState());
        return deviceDtos;
    }
//
    @PostMapping
    public DeviceGroupDto create(@RequestBody DeviceGroupDto dto) {
        DeviceGroup deviceGroup = new DeviceGroup(dto);
        deviceGroup.setDevices(deviceDao.findAllById(dto.getDevices()));
        deviceGroup.updateStatus();
        deviceGroup = deviceGroupDao.save(deviceGroup);

        return new DeviceGroupDto(deviceGroup);
    }

    @PutMapping(path = "/{id}")
    public DeviceGroupDto update(@RequestBody DeviceGroupDto dto) {
        DeviceGroup deviceGroup = deviceGroupDao.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException());
        deviceGroup.setName(dto.getName());
        deviceGroup.setDevices(deviceDao.findAllById(dto.getDevices()));
        deviceGroup = deviceGroupDao.save(deviceGroup);

        return new DeviceGroupDto(deviceGroup);
    }

    @DeleteMapping(path="/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        DeviceGroup deviceGroup = deviceGroupDao.findById(id).orElseThrow(IllegalArgumentException::new);
        deviceGroup.getDevices().clear();
        deviceGroupDao.delete(deviceGroup);
    }

}
