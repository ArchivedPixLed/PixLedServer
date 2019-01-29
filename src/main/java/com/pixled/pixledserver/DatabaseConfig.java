package com.pixled.pixledserver;

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
    public DatabaseConfig(DeviceGroupDao deviceGroupDao) {
        DeviceGroup deviceGroup = new DeviceGroup("Test Group");
        deviceGroupDao.save(deviceGroup);

        Device testDevice1 = new Strip(15);
        Device testDevice2 = new Strip(30);
        deviceGroup.getDevices().add(testDevice1);
        deviceGroup.getDevices().add(testDevice2);
        deviceGroupDao.save(deviceGroup);
    }
}
