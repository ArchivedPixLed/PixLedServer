package com.pixled.pixledserver.model.device.strip.dao;

import com.pixled.pixledserver.core.device.strip.Strip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StripDao extends JpaRepository<Strip, Integer> {
}
