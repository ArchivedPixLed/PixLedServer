package com.esme.spring.faircorp.model.room;

import java.util.List;

public interface RoomDaoCustom {
    List<Room> findByName(String name);
}
