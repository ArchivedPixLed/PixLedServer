package com.esme.spring.pixledserver.model.building.dao;

import com.esme.spring.pixledserver.model.light.Light;

import java.util.List;

public interface BuildingDaoCustom {
    List<Light> findAllBuildingLights(Long idBuilding);
}
