package com.esme.spring.pixledserver.model.light;

import com.esme.spring.pixledserver.model.room.Room;
import com.esme.spring.pixledserver.model.Status;

import javax.persistence.*;

@Entity
public class Light {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer level;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne()
    private Room room;

    @Column(nullable = false)
    private Integer color;

    public Light () {
    }

    public Light(Integer level, Status status, Room room) {
        this.level = level;
        this.status = status;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public void switchLight() {
        this.status = (this.status == Status.ON) ? Status.OFF : Status.ON;
        room.updateStatus();
    }
}
