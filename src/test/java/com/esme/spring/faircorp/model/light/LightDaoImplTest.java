package com.esme.spring.faircorp.model.light;

import com.esme.spring.faircorp.model.Status;
import com.esme.spring.faircorp.model.light.dao.LightDao;
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
public class LightDaoImplTest {

    @Autowired
    LightDao lightDao;

    @Test
    public void shouldFindOnLights() {
        assertThat(lightDao.findOnLights())
                .hasSize(1)
                .extracting("id", "status")
                .containsExactly(tuple(-1L, Status.ON));
    }

    @Test
    public void shouldFindRoomLights() {
        assertThat(lightDao.findByRoomId(-10L))
                .hasSize(2)
                .extracting("id")
                .contains(-1L, -2L);
    }
}
