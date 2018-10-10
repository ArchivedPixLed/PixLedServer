package com.esme.spring.faircorp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id_light;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer floor;

    @OneToMany(mappedBy = "light")
    private List<Light> lights;

    public Room(Long id_light, String name, Integer floor, List<Light> lights) {
        this.id_light = id_light;
        this.name = name;
        this.floor = floor;
        this.lights = lights;
    }

    public Long getId_light() {
        return id_light;
    }

    public void setId_light(Long id_light) {
        this.id_light = id_light;
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
}
