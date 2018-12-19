package com.esme.spring.pixledserver.model.light.dao;

import com.esme.spring.pixledserver.model.light.Light;

import java.util.List;

public interface LightDaoCustom {
    List<Light> findOnLights();
}
