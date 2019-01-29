package com.pixled.pixledserver.model.state.device.dto;

import com.pixled.pixledserver.model.state.base.dto.StateDto;
import com.pixled.pixledserver.core.state.device.DeviceState;

public class DeviceStateDto extends StateDto {

    private boolean connected;

    public DeviceStateDto(DeviceState deviceState) {
        super(deviceState);
        connected = deviceState.isConnected();
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
