package com.esme.spring.pixledserver.model.room.dto;

import com.esme.spring.pixledserver.model.room.Room;

public class RoomDto {

    private Long id;
    private String name;
    private Integer floor;
    private Long buildingId;

    public RoomDto(){
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.buildingId = room.getBuilding().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public Long getBuildingId() {
        return buildingId;
    }
}
