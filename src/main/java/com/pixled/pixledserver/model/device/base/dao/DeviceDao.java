package com.pixled.pixledserver.model.device.base.dao;

import com.pixled.pixledserver.model.device.base.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDao extends JpaRepository<Device, Integer> {
}
