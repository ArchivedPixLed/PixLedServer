package com.esme.spring.faircorp.model.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCustom {
    List<Room> findByName(String name);
}
