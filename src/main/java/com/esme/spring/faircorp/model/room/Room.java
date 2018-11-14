package com.esme.spring.faircorp.model.room;

import com.esme.spring.faircorp.model.building.Building;
import com.esme.spring.faircorp.model.light.Light;

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

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Light> lights;

    @ManyToOne
    private Building building;

    public Room() {
    }

    public Room(String name, Integer floor, Building building) {
        this(name, floor, new ArrayList<>(), building);
    }

    public Room(String name, Integer floor, List<Light> lights, Building building) {
        this.name = name;
        this.floor = floor;
        this.lights = lights;
        this.building = building;
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
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
