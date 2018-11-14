package com.esme.spring.faircorp.model.light.dao;

import com.esme.spring.faircorp.model.light.Light;

import java.util.List;

public interface LightDaoCustom {
    List<Light> findOnLights();
}
