package com.esme.spring.pixledserver.model.room.dao;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.light.Light;
import com.esme.spring.pixledserver.model.light.dao.LightDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomDaoImpl implements RoomDaoCustom {

    @Autowired
    private LightDao lightDao;

    @Override
    public boolean roomLightById(Long id) {

        List<Light> roomLights = lightDao.findByRoomId(id);
        for (Light light : roomLights) {
            if (light.getStatus() == Status.ON){
                return true;
            }
        }
        return false;
    }
}
