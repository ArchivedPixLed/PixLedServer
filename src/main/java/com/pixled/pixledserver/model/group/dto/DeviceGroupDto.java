package com.pixled.pixledserver.model.group.dto;

import com.pixled.pixledserver.model.group.DeviceGroup;
import com.pixled.pixledserver.state.deviceGroup.dto.DeviceGroupStateDto;

public class DeviceGroupDto {

    private Integer id;
    private String name;
    private DeviceGroupStateDto state;

    public DeviceGroupDto(){
    }

    public DeviceGroupDto(DeviceGroup deviceGroup) {
        id = deviceGroup.getId();
        name = deviceGroup.getName();
        state = new DeviceGroupStateDto(deviceGroup.getDeviceGroupState());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceGroupStateDto getState() {
        return state;
    }

    public void setState(DeviceGroupStateDto state) {
        this.state = state;
    }
}
