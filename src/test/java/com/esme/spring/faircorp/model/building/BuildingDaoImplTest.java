package com.esme.spring.faircorp.model.building;

import com.esme.spring.faircorp.model.building.dao.BuildingDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class BuildingDaoImplTest {

    @Autowired
    BuildingDao buildingDao;

    @Test
    public void shouldFindAllBuildingLights() {
        assertThat(buildingDao.findAllBuildingLights(-1L))
                .hasSize(2)
                .extracting("id")
                .containsExactlyInAnyOrder(-1L, -2L);
    }
}
