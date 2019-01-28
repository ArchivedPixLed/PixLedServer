package com.pixled.pixledserver.model.device.base.dto;

import com.pixled.pixledserver.model.device.base.Device;
import com.pixled.pixledserver.model.group.DeviceGroup;
import com.pixled.pixledserver.state.base.dto.StateDto;
import com.pixled.pixledserver.state.device.dto.DeviceStateDto;

import java.util.ArrayList;
import java.util.List;

public abstract class DeviceDto {
    private Integer id;
    private List<Integer> deviceGroups;
    private DeviceStateDto state;

    public DeviceDto() {
    }

    public DeviceDto(Device device) {
        deviceGroups = new ArrayList<>();
        id = device.getId();
        state = new DeviceStateDto(device.getDeviceState());
        for (DeviceGroup deviceGroup : device.getDeviceGroups()) {
            deviceGroups.add(deviceGroup.getId());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(List<Integer> deviceGroupIds) {
        this.deviceGroups = deviceGroupIds;
    }

    public StateDto getState() {
        return state;
    }

    public void setState(DeviceStateDto state) {
        this.state = state;
    }

    public void setDeviceState(DeviceStateDto deviceStateDto) {
        state = deviceStateDto;
    }

}
