package com.esme.spring.faircorp.model.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCustom {
}
