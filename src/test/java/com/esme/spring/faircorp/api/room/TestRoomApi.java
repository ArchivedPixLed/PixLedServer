package com.esme.spring.faircorp.api.room;

import com.esme.spring.faircorp.model.room.dao.RoomDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRoomApi {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RoomDao roomDao;

    @Test
    public void testSwitchLights() throws Exception {
        restTemplate.put("http://localhost:" + port + "/api/rooms/-10/switchLight", String.class);
        Assertions.assertThat(roomDao.roomLightById(-10L)).isFalse();
        restTemplate.put("http://localhost:" + port + "/api/rooms/-10/switchLight", String.class);
        Assertions.assertThat(roomDao.roomLightById(-10L)).isTrue();
    }

    @Test
    public void testGetRoomLights() throws Exception {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:" + port + "/api/rooms/-10/lights", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode id = root.get("id");
        JsonNode level = root.get("level");
        JsonNode status = root.path("id");
        JsonNode roomId = root.path("roomId");
        Assertions.assertThat(root).isNotEmpty();
    }
}
