package com.pixled.pixledserver.model.device.strip;

import com.pixled.pixledserver.model.device.base.Device;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Strip extends Device {

    @Column
    private Integer ledCount;

    public Strip(){
        super();
    }

    public Strip(Integer ledCount) {
        super();
        this.ledCount = ledCount;
    }

    public Integer getLedCount() {
        return ledCount;
    }

    public void setLedCount(Integer ledCount) {
        this.ledCount = ledCount;
    }

}
