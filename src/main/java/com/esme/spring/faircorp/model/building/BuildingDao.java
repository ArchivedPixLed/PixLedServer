package com.esme.spring.faircorp.model.building;

import com.esme.spring.faircorp.model.light.Light;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingDaoCustom {
}
