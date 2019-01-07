package com.esme.spring.pixledserver.model.light;

import com.esme.spring.pixledserver.model.color.Color;
import com.esme.spring.pixledserver.model.color.dao.ColorDao;
import com.esme.spring.pixledserver.model.room.Room;
import com.esme.spring.pixledserver.model.Status;
import org.springframework.beans.factory.annotation.Autowired;

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
    private Boolean connected;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Color color;

    public Light () {
    }

    public Light(Integer level, Status status, Room room) {
        this.level = level;
        this.status = status;
        this.room = room;
        this.color = new Color(0f, 0f, 1f, -1);
        this.connected = false;
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

    public Color getColor() {
        return color;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public void switchLight() {
        this.status = (this.status == Status.ON) ? Status.OFF : Status.ON;
        room.updateStatus();
    }
}
