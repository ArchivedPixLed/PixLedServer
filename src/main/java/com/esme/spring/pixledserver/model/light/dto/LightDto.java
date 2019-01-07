package com.esme.spring.pixledserver.model.light.dto;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.light.Light;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class LightDto {
    private Long id;
    private Integer level;
    private Status status;
    private Float hue;
    private Float saturation;
    private Float value;
    private Boolean connected;

    private Long roomId;

    public LightDto() {
    }

    public LightDto(Light light) {
        System.out.println("Init light");
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
        System.out.println("status ok");
        this.hue = light.getColor().getHue();
        this.saturation = light.getColor().getSaturation();
        this.value = light.getColor().getValue();
        System.out.println("color ok");
        this.roomId = light.getRoom().getId();
        this.connected = light.getConnected();
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

    public Float getHue() {
        return hue;
    }

    public Float getSaturation() {
        return saturation;
    }

    public Float getValue() {
        return value;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Boolean getConnected() {
        return connected;
    }
}
