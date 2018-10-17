package com.esme.spring.faircorp.model.building;

import com.esme.spring.faircorp.model.light.Light;

import java.util.List;

public interface BuildingDaoCustom {
    List<Light> findAllBuildingLights(Long idBuilding);
}
