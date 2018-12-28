package com.esme.spring.pixledserver.model.light;

public class Color {
    private Float hue;
    private Float saturation;
    private Float value;
    private Integer argb;

    public Color(Float hue, Float saturation, Float value, Integer argb) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
        this.argb = argb;
    }

    public Float getHue() {
        return hue;
    }

    public void setHue(Float hue) {
        this.hue = hue;
    }

    public Float getSaturation() {
        return saturation;
    }

    public void setSaturation(Float saturation) {
        this.saturation = saturation;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getArgb() {
        return argb;
    }

    public void setArgb(Integer argb) {
        this.argb = argb;
    }
}
