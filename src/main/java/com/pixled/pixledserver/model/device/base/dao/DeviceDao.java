package com.pixled.pixledserver.model.device.base.dao;

import com.pixled.pixledserver.model.device.base.Device;
import com.pixled.pixledserver.model.group.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDao extends JpaRepository<Device, Integer> {
    List<Device> findByDeviceGroups(DeviceGroup deviceGroup);
}
