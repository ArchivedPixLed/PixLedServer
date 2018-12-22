package com.esme.spring.pixledserver.model.light.dto;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.light.Light;

public class LightDto {
    private Long id;
    private Integer level;
    private Status status;
    private Integer color;
    private Long roomId;

    public LightDto() {
    }

    public LightDto(Light light) {
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
        this.color = light.getColor();
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

    public Integer getColor() {
        return color;
    }

    public Long getRoomId() {
        return roomId;
    }
}
