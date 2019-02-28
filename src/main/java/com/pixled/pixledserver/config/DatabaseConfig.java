package com.pixled.pixledserver.config;

import com.pixled.pixledserver.core.device.base.Device;
import com.pixled.pixledserver.model.device.base.dao.DeviceDao;
import com.pixled.pixledserver.core.device.strip.Strip;
import com.pixled.pixledserver.core.group.DeviceGroup;
import com.pixled.pixledserver.model.group.dao.DeviceGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Autowired
    public DatabaseConfig(DeviceGroupDao deviceGroupDao, DeviceDao deviceDao) {
        /*
        Test Data
         */

//        Device testDevice1 = new Strip("Led Strip 1", 15);
//        testDevice1.getDeviceState().setConnected(true);
//        Device testDevice2 = new Strip("Desk", 30);
//        testDevice2.getDeviceState().setConnected(true);
//
//        Device testDevice3 = new Strip("Bed", 30);
//        testDevice3.getDeviceState().setConnected(true);
//        Device testDevice4 = new Strip("Led Strip 2", 30);
//
//
//        deviceDao.save(testDevice1);
//        deviceDao.save(testDevice2);
//        deviceDao.save(testDevice3);
//        deviceDao.save(testDevice4);
    }
}
