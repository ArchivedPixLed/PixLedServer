package com.esme.spring.pixledserver.model.color.dao;

import com.esme.spring.pixledserver.model.color.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color, Long> {
}
