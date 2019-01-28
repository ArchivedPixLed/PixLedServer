package com.pixled.pixledserver.model.device.strip.dto;

import com.pixled.pixledserver.model.device.base.dto.DeviceDto;
import com.pixled.pixledserver.model.device.strip.Strip;

public class StripDto extends DeviceDto {

    private Integer ledCount;

    public StripDto(Strip strip) {
        super(strip);
        ledCount = strip.getLedCount();
    }

    public Integer getLedCount() {
        return ledCount;
    }

    public void setLedCount(Integer ledCount) {
        this.ledCount = ledCount;
    }
}
