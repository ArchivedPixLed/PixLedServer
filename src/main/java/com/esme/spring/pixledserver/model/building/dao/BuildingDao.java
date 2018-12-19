package com.esme.spring.pixledserver.model.building.dao;

import com.esme.spring.pixledserver.model.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingDaoCustom {
}
