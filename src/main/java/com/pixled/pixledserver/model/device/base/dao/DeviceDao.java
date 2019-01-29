package com.pixled.pixledserver.model.device.base.dao;

import com.pixled.pixledserver.core.device.base.Device;
import com.pixled.pixledserver.core.group.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDao extends JpaRepository<Device, Integer> {
    List<Device> findByDeviceGroups(DeviceGroup deviceGroup);
}
