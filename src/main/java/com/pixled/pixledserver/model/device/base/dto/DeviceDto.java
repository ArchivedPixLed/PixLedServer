package com.pixled.pixledserver.model.device.base.dto;

import com.pixled.pixledserver.model.device.base.Device;
import com.pixled.pixledserver.model.group.DeviceGroup;
import com.pixled.pixledserver.state.base.dto.StateDto;
import com.pixled.pixledserver.state.device.dto.DeviceStateDto;

import java.util.List;

public class DeviceDto {
    private Integer id;
    private List<Integer> deviceGroupIds;
    private DeviceStateDto state;

    private Long roomId;

    public DeviceDto() {
    }

    public DeviceDto(Device device) {
        id = device.getId();
        state = new DeviceStateDto(device.getDeviceState());
        for (DeviceGroup deviceGroup : device.getDeviceGroups()) {
            deviceGroupIds.add(deviceGroup.getId());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getDeviceGroupIds() {
        return deviceGroupIds;
    }

    public void setDeviceGroupIds(List<Integer> deviceGroupIds) {
        this.deviceGroupIds = deviceGroupIds;
    }

    public StateDto getState() {
        return state;
    }

    public void setDeviceState(DeviceStateDto deviceStateDto) {
        state = deviceStateDto;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
