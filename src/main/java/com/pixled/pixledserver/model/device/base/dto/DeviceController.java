package com.pixled.pixledserver.model.device.base.dto;

import com.pixled.pixledserver.core.color.ColorDto;
import com.pixled.pixledserver.core.device.base.DeviceDto;
import com.pixled.pixledserver.core.group.DeviceGroup;
import com.pixled.pixledserver.model.color.dao.ColorDao;
import com.pixled.pixledserver.core.device.base.Device;
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
@RequestMapping("/api/devices")
@Transactional
public class DeviceController {

    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private DeviceGroupDao deviceGroupDao;
    @Autowired
    private MqttConnection mqttConnection;


    @GetMapping
    public List<DeviceDto> findAll() {
        return deviceDao.findAll()
                .stream()
                .map(device -> device.generateDto())
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public DeviceDto findById(@PathVariable Integer id) {
        return deviceDao.findById(id).map(device -> device.generateDto()).orElse(null);
    }

    @PostMapping()
    public DeviceDto createDevice(@RequestBody DeviceDto deviceDto) {
        Device newDevice = deviceDto.generateDevice();
        newDevice = deviceDao.save(newDevice);

        return newDevice.generateDto();
    }

    @PutMapping(path = "/{id}/switch")
    public DeviceDto switchDevice(@PathVariable Integer id) {
        Device device = deviceDao.findById(id).orElseThrow(IllegalArgumentException::new);
        device.switchDevice();
        deviceDao.save(device);
        // deviceGroupDao.save(light.getRoom());
        if(mqttConnection.isConnected()) {
            mqttConnection.publishDeviceSwitch(
                    device.getId(),
                    device.getDeviceState().getToggleState());
        }
        return device.generateDto();
    }
//
    @PutMapping(path = "/{id}/color")
    public DeviceDto changeColor(@PathVariable Integer id, @RequestBody ColorDto color) {
        Device device = deviceDao.findById(id).orElseThrow(IllegalArgumentException::new);
        device.getDeviceState().getColor().setHue(color.getHue());
        device.getDeviceState().getColor().setSaturation(color.getSaturation());
        device.getDeviceState().getColor().setValue(color.getValue());
        colorDao.save(device.getDeviceState().getColor());
        if (mqttConnection.isConnected()) {
            mqttConnection.publishDeviceColor(
                    device.getId(),
                    device.getDeviceState().getColor().getArgb().toString());
        }


        return device.generateDto();
    }

    @PutMapping(path = "/{id}")
    public DeviceDto updateDevice(@PathVariable Integer id, @RequestBody DeviceDto deviceDto) {
        Device device = deviceDto.generateDevice();
        device = deviceDao.save(device);
        return device.generateDto();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDevice(@PathVariable Integer id) {
        Device device = deviceDao.findById(id).orElseThrow(IllegalArgumentException::new);
        for (DeviceGroup deviceGroup : device.getDeviceGroups()) {
            deviceGroup.getDevices().remove(device);
        }
        deviceDao.delete(device);
    }
}
