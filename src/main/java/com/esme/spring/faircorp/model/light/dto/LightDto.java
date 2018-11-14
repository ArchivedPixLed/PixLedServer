package com.esme.spring.faircorp.model.light.dto;

import com.esme.spring.faircorp.model.Status;
import com.esme.spring.faircorp.model.light.Light;

public class LightDto {
    private Long id;
    private Integer level;
    private Status status;
    private Long roomId;

    public LightDto() {
    }

    public LightDto(Light light) {
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
        this.roomId = light.getRoom().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public Long getRoomId() {
        return roomId;
    }
}
