package com.esme.spring.pixledserver.model.light.dto;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.light.Light;
import com.esme.spring.pixledserver.model.light.dao.LightDao;
import com.esme.spring.pixledserver.model.room.dao.RoomDao;
import com.esme.spring.pixledserver.mqtt.MqttConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/lights")
@Transactional
public class LightController {

    @Autowired
    private LightDao lightDao;
    @Autowired
    private RoomDao roomDao;
//    @Autowired
//    private MqttConnection mqttConnection;


    @GetMapping
    public List<LightDto> findAll() {
        return lightDao.findAll()
                .stream()
                .map(LightDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public LightDto findById(@PathVariable Long id) {
        return lightDao.findById(id).map(light -> new LightDto(light)).orElse(null);
    }

    @PutMapping(path = "/{id}/switch")
    public LightDto switchStatus(@PathVariable Long id) {
        Light light = lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.switchLight();
        lightDao.save(light);
        roomDao.save(light.getRoom());
//        mqttConnection.publishSwitch(
//                light.getRoom().getBuilding().getId(),
//                light.getRoom().getId(),
//                light.getId(),
//                light.getStatus());
        return new LightDto(light);
    }

    @PutMapping(path = "/{id}/color")
    public LightDto changeColor(@PathVariable Long id, @RequestBody ColorDto color) {
        Light light = lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setHue(color.getHue());
        light.setSaturation(color.getSaturation());
        light.setValue(color.getValue());
        lightDao.save(light);
//        mqttConnection.publishColor(
//                light.getRoom().getBuilding().getId(),
//                light.getRoom().getId(),
//                light.getId(),
//                color.getArgb().toString());

        return new LightDto(light);
    }

    @PostMapping
    public LightDto create(@RequestBody LightDto dto) {
        Light light = null;
        if (dto.getId() != null) {
            light = lightDao.findById(dto.getId()).orElse(null);
        }

        if (light == null) {
            light = lightDao.save(new Light(dto.getLevel(), dto.getStatus(), roomDao.getOne(dto.getRoomId())));
        } else {
            if (dto.getLevel() != null) {
                light.setLevel(dto.getLevel());
            }
            if (dto.getStatus() != null) {
                light.setStatus(dto.getStatus());
            }
            lightDao.save(light);
        }

        return new LightDto(light);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        lightDao.deleteById(id);
    }
}
