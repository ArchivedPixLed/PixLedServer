package com.esme.spring.faircorp.model.room.dto;

import com.esme.spring.faircorp.model.Status;
import com.esme.spring.faircorp.model.building.dao.BuildingDao;
import com.esme.spring.faircorp.model.light.Light;
import com.esme.spring.faircorp.model.light.dao.LightDao;
import com.esme.spring.faircorp.model.room.Room;
import com.esme.spring.faircorp.model.room.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    @Autowired
    LightDao lightDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    BuildingDao buildingDao;

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path = "/{id}/switchLight")
    public RoomDto switchLight(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        if(roomDao.roomLightById(room.getId())){
            for (Light light : room.getLights()){
                if (light.getStatus() == Status.ON) {
                    light.setStatus(Status.OFF);
                    lightDao.save(light);
                }
            }
        }
        else {
            for (Light light : room.getLights()){
                light.setStatus(Status.ON);
                lightDao.save(light);
            }
        }
        return new RoomDto(room);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() != null) {
            room = roomDao.findById(dto.getId()).orElse(null);
        }

        if (room == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor(), buildingDao.getOne(dto.getBuildingId())));
        } else {
            roomDao.save(room);
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }

}
