package com.pixled.pixledserver.model.group;

import com.pixled.pixledserver.model.group.dao.DeviceGroupDao;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class RoomDaoImplTest {

    @Autowired
    DeviceGroupDao roomDao;

    @Test
    public void shouldFindOnLights() {
        Assertions.assertThat(roomDao.findByName("Room1"))
                .hasSize(1)
                .extracting("id", "floor")
                .containsExactly(tuple(-10L, 1));
    }

//    @Test
//    public void shouldFindRoomLight() {
//        assertThat(roomDao.roomLightById(-10L)).isTrue();
//        assertThat(roomDao.roomLightById(-9L)).isFalse();
//    }
}
