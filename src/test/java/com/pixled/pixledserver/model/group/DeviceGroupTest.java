package com.pixled.pixledserver.model.group;

import com.pixled.pixledserver.core.device.base.Device;
import com.pixled.pixledserver.core.device.strip.Strip;
import com.pixled.pixledserver.core.group.DeviceGroup;
import com.pixled.pixledserver.model.group.dao.DeviceGroupDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
@ComponentScan(basePackages = "com.pixled.pixledserver.mqtt")
public class DeviceGroupTest {

    @Autowired
    private DeviceGroupDao deviceGroupDao;

    @Test
    public void addDeviceGroup() {
        DeviceGroup deviceGroup = new DeviceGroup("Test Group");
        deviceGroupDao.save(deviceGroup);

        Device testDevice1 = new Strip("Strip1", 15);
        Device testDevice2 = new Strip("Strip2",30);
        deviceGroup.getDevices().add(testDevice1);
        deviceGroup.getDevices().add(testDevice2);
        deviceGroupDao.save(deviceGroup);

        DeviceGroup loadedDeviceGroup = deviceGroupDao.findById(deviceGroup.getId()).orElseThrow(() -> new IllegalArgumentException());
        assertThat(loadedDeviceGroup).isNotEqualTo(null);
        assertThat(loadedDeviceGroup.getDevices()).hasSize(2);
    }
}
