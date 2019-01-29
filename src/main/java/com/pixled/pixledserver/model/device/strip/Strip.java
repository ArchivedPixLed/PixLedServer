package com.pixled.pixledserver.model.device.strip;

import com.pixled.pixledserver.model.device.base.Device;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Strip extends Device {

    @Column
    private Integer length;

    public Strip(){
        super();
    }

    public Strip(Integer length) {
        super();
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

}
