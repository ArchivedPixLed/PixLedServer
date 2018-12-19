package com.esme.spring.pixledserver.model.light.dao;

import com.esme.spring.pixledserver.model.light.Light;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LightDao extends JpaRepository<Light, Long>, LightDaoCustom {
    List<Light> findByRoomId(Long id);
}
