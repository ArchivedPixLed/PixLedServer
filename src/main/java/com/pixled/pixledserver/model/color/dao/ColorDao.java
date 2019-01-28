package com.pixled.pixledserver.model.color.dao;

import com.pixled.pixledserver.model.color.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color, Long> {
}
