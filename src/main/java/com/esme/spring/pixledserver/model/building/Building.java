package com.esme.spring.pixledserver.model.building;

import com.esme.spring.pixledserver.model.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "building", cascade = CascadeType.REMOVE)
    List<Room> rooms;

    public Building() {
    }

    public Building(String name){
        this(name, new ArrayList<>());
    }

    public Building(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
