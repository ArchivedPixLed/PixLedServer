package com.esme.spring.pixledserver.model.room;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.building.Building;
import com.esme.spring.pixledserver.model.light.Light;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer floor;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Light> lights;

    @ManyToOne
    private Building building;


    public Room() {
    }

    public Room(String name, Integer floor, Building building) {
        this(name, floor, Status.OFF, new ArrayList<>(), building);
    }

    public Room(String name, Integer floor, Status status, List<Light> lights, Building building) {
        this.name = name;
        this.floor = floor;
        this.lights = lights;
        this.building = building;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
        updateStatus();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void updateStatus() {
        Status status = Status.OFF;
        int i = 0;
        while (status == Status.OFF && i < lights.size()) {
            if (lights.get(i).getStatus() == Status.ON) {
                status = Status.ON;
            }
            i++;
        }
        this.status = status;
    }

    public void switchRoom() {
        this.status = (this.status == Status.ON) ? Status.OFF : Status.ON;
        for (Light light : lights) {
            light.setStatus(this.status);
        }
    }
}
