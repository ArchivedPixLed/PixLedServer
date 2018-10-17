package com.esme.spring.faircorp.model.building;

import com.esme.spring.faircorp.model.light.Light;
import com.esme.spring.faircorp.model.light.LightDao;
import com.esme.spring.faircorp.model.room.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom {

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private LightDao lightDao;

    @Override
    public List<Light> findAllBuildingLights(Long idBuilding) {
        List<Light> buildingLights = new ArrayList<>();
        if (buildingDao.findById(idBuilding).isPresent()) {
            for (Room buildingRoom : buildingDao.findById(idBuilding).get().getRooms()) {
                buildingLights.addAll(lightDao.findByRoomId(buildingRoom.getId()));
            }
        }
        return buildingLights;
    }

}
