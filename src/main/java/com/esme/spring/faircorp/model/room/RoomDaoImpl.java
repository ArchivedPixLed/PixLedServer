package com.esme.spring.faircorp.model.room;

import com.esme.spring.faircorp.model.Status;
import com.esme.spring.faircorp.model.light.Light;
import com.esme.spring.faircorp.model.light.LightDao;
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
