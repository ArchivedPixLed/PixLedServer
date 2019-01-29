package com.pixled.pixledserver.model.device.strip.dto;

import com.pixled.pixledserver.model.device.base.dto.DeviceDto;
import com.pixled.pixledserver.model.device.strip.Strip;

public class StripDto extends DeviceDto {

    private Integer length;

    public StripDto(Strip strip) {
        super(strip);
        length = strip.getLength();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
