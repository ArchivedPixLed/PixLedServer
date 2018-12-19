package com.esme.spring.pixledserver.model.building.dto;

import com.esme.spring.pixledserver.model.building.Building;
import com.esme.spring.pixledserver.model.building.dao.BuildingDao;
import com.esme.spring.pixledserver.model.room.dao.RoomDao;
import com.esme.spring.pixledserver.model.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {

    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private RoomDao roomDao;

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id)
                .map(building -> new BuildingDto(building))
                .orElse(null);
    }

    @GetMapping(path = "/{id}/rooms")
    public List<RoomDto> findBuildingRooms(@PathVariable Long id) {
        return roomDao.findByBuildingId(id)
                .stream()
                .map(room -> new RoomDto(room))
                .collect(Collectors.toList());
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() != null) {
            building = buildingDao.findById(dto.getId()).orElse(null);
        }

        if (building == null) {
            building = buildingDao.save(new Building(dto.getName()));
        } else {
            buildingDao.save(building);
        }

        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        buildingDao.deleteById(id);
    }
}
