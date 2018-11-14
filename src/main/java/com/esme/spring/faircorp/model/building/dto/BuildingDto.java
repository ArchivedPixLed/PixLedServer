package com.esme.spring.faircorp.model.building.dto;

import com.esme.spring.faircorp.model.building.Building;
import com.esme.spring.faircorp.model.room.Room;

import java.util.List;

public class BuildingDto {
    private Long id;
    private String name;

    public BuildingDto() {

    }

    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
