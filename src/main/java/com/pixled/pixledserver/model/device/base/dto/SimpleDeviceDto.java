package com.pixled.pixledserver.model.device.base.dto;

import com.pixled.pixledserver.core.device.base.Device;

public class SimpleDeviceDto extends DeviceDto {

    private String type;

    public SimpleDeviceDto(Device device) {
        super(device);
        type = device.getClass().getSimpleName();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
