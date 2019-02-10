package com.pixled.pixledserver.model.device.strip.dto;

import com.pixled.pixledserver.core.device.base.Device;
import com.pixled.pixledserver.core.device.base.DeviceDto;
import com.pixled.pixledserver.core.device.strip.Strip;
import com.pixled.pixledserver.core.device.strip.StripDto;
import com.pixled.pixledserver.model.device.strip.dao.StripDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/devices/strips")
@Transactional
public class StripController {

    @Autowired
    private StripDao stripDao;

    @GetMapping
    public List<StripDto> findAll() {
        return stripDao.findAll()
                .stream()
                .map(strip -> strip.generateDto())
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public StripDto findById(@PathVariable Integer id) {
        return stripDao.findById(id).map(strip -> new StripDto(strip)).orElse(null);
    }

    @PutMapping(path = "/{id}/switch")
    public DeviceDto switchDevice(@PathVariable Integer id) {
        Strip strip = stripDao.findById(id).orElseThrow(IllegalArgumentException::new);
        strip.switchDevice();
        stripDao.save(strip);
        // deviceGroupDao.save(light.getRoom());
//        mqttConnection.publishSwitch(
//                light.getRoom().getBuilding().getId(),
//                light.getRoom().getId(),
//                light.getId(),
//                light.getStatus());
        return new StripDto(strip);
    }
}
