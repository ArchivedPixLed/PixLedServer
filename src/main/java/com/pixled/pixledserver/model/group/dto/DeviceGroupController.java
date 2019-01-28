package com.pixled.pixledserver.model.group.dto;

import com.pixled.pixledserver.model.device.base.dao.DeviceDao;
import com.pixled.pixledserver.model.group.dao.DeviceGroupDao;
import com.pixled.pixledserver.mqtt.MqttConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/rooms")
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
//
//    @GetMapping(path = "/{id}/lights")
//    public List<DeviceDto> findGroupDevices(@PathVariable Long id) {
//        return deviceDao.findByDeviceGroupId(id)
//                .stream()
//                .map(light -> new DeviceDto(light))
//                .collect(Collectors.toList());
//    }

//    @PutMapping(path = "/{id}/switch")
//    public List<DeviceDto> switchLight(@PathVariable Long id) {
//        DeviceGroup room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
////        if(roomDao.roomLightById(room.getId())){
////            for (Light light : room.getLights()){
////                if (light.getStatus() == Status.ON) {
////                    light.setStatus(Status.OFF);
////                    lightDao.save(light);
////                }
////            }
////        }
////        else {
////            for (Light light : room.getLights()){
////                light.setStatus(Status.ON);
////                lightDao.save(light);
////            }
////        }
//        room.switchRoom();
//        roomDao.save(room);
//        ArrayList<DeviceDto> lightDtos = new ArrayList<>();
//        for (Device light : room.getLights()) {
//            lightDao.save(light);
//            lightDtos.add(new DeviceDto(light));
//            mqttConnection.publishSwitch(
//                    room.getBuilding().getId(),
//                    room.getId(),
//                    light.getId(),
//                    light.getStatus());
//        }
//        return lightDtos;
//    }
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
