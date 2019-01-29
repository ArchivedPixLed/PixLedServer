package com.pixled.pixledserver.model.color.dao;

import com.pixled.pixledserver.core.color.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color, Long> {
}
