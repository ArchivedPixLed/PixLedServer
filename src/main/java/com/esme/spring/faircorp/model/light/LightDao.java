package com.esme.spring.faircorp.model.light;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LightDao extends JpaRepository<Light, Long>, LightDaoCustom {
    List<Light> findByRoomId(Long id);
}
