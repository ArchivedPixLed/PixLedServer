package com.pixled.pixledserver.model.device.base;

import com.pixled.pixledserver.core.device.base.Device;
import com.pixled.pixledserver.model.device.base.dao.DeviceDao;
import com.pixled.pixledserver.core.device.strip.Strip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
@ComponentScan(basePackages = "com.pixled.pixledserver.mqtt")
public class DeviceDaoTest {

    @Autowired
    private DeviceDao deviceDao;

    @Test
    public void addStrips() {
        Strip stripDevice1 = new Strip(20);
        deviceDao.save(stripDevice1);

        Strip stripDevice2 = new Strip(10);
        deviceDao.save(stripDevice2);

        List<Device> strips = deviceDao.findAll();
        assertThat(deviceDao.findAll()).hasSize(2);
        List<Integer> ledCounts = new ArrayList<>();
        for (Device device : strips) {
            ledCounts.add(((Strip) device).getLength());
        }
        assertThat(ledCounts).contains(20);
        assertThat(ledCounts).contains(10);
    }
}
