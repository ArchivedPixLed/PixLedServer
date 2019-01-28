package com.pixled.pixledserver.model.device.base;

import com.pixled.pixledserver.model.color.Color;
import com.pixled.pixledserver.model.group.DeviceGroup;
import com.pixled.pixledserver.model.ToggleState;
import com.pixled.pixledserver.state.device.DeviceState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Device {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "devices", cascade = CascadeType.ALL)
    private List<DeviceGroup> deviceGroups;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn()
    private DeviceState deviceState;

    public Device() {
        deviceGroups = new ArrayList<>();
        deviceState = new DeviceState();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DeviceGroup> getDeviceGroups() {
        return deviceGroups;
    }

    public DeviceState getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    public void switchDevice() {
        deviceState.setToggleState(deviceState.getToggleState() == ToggleState.ON ? ToggleState.OFF : ToggleState.ON);
        for (DeviceGroup deviceGroup : deviceGroups) {
            deviceGroup.updateStatus();
        }
    }
}
