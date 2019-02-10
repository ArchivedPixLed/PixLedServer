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
        DeviceGroup deviceGroup1 = new DeviceGroup("Test Group 1");
        deviceGroupDao.save(deviceGroup1);

        Device testDevice1 = new Strip(15);
        testDevice1.setName("Strip 1");
        Device testDevice2 = new Strip(30);
        testDevice2.setName("Strip 2");
        deviceGroup1.getDevices().add(testDevice1);
        deviceGroup1.getDevices().add(testDevice2);
        deviceGroupDao.save(deviceGroup1);

        Device testDevice3 = new Strip(30);
        testDevice3.setName("Strip 3");
        deviceDao.save(testDevice3);

        DeviceGroup deviceGroup2 = new DeviceGroup("Test Group 2");
        deviceGroupDao.save(deviceGroup2);

        DeviceGroup deviceGroup3 = new DeviceGroup("Test Group 3");
        deviceGroupDao.save(deviceGroup3);
    }
}
