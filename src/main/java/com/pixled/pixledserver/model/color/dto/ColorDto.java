package com.pixled.pixledserver.model.color.dto;

import com.pixled.pixledserver.core.color.Color;

public class ColorDto {
    private Float hue;
    private Float saturation;
    private Float value;
    private Integer argb;

    public ColorDto() {

    }

    private ColorDto(Color color) {
        this.hue = color.getHue();
        this.saturation = color.getSaturation();
        this.value = color.getValue();
        this.argb = color.getArgb();
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

    public Integer getArgb() {
        return argb;
    }
}
