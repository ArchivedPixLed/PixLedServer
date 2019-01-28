package com.pixled.pixledserver.model.group.dao;

import com.pixled.pixledserver.model.group.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceGroupDao extends JpaRepository<DeviceGroup, Integer> {
    List<DeviceGroup> findByName(String name);
}
