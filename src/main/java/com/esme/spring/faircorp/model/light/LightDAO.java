package com.esme.spring.faircorp.model.light;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LightDAO extends JpaRepository<Light, Long>, LightDaoCustom {

}
