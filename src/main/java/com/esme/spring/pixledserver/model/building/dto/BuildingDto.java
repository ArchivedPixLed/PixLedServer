package com.esme.spring.pixledserver.model.building.dto;

import com.esme.spring.pixledserver.model.building.Building;

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
