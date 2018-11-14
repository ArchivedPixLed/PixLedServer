package com.esme.spring.faircorp.model.room.dao;

import com.esme.spring.faircorp.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCustom {
    List<Room> findByName(String name);
}
