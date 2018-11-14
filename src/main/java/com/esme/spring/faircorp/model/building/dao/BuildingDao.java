package com.esme.spring.faircorp.model.building.dao;

import com.esme.spring.faircorp.model.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingDaoCustom {
}
