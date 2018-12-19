package com.esme.spring.pixledserver.api.light;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestLightApi {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllLights() throws Exception {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:" + port + "/api/lights", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        Assertions.assertThat(root).isNotEmpty();
    }

    @Test
    public void testGetLightById() throws Exception {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:" + port + "/api/lights/-2", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        int id = root.get("id").intValue();
        int level = root.get("level").intValue();
        String status = root.path("status").textValue();
        int roomId = root.path("roomId").intValue();
        Assertions.assertThat(id).isEqualTo(-2);
        Assertions.assertThat(level).isEqualTo(0);
        Assertions.assertThat(status).isEqualTo("OFF");
        Assertions.assertThat(roomId).isEqualTo(-10);
    }

    @Test
    public void createNewLight() throws Exception {
        String requestJson =
                "{" +
                        "\"level\": 2," +
                        "\"status\" : \"ON\"," +
                        "\"roomId\" : -9" +
                        "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
        ResponseEntity<String> answer = restTemplate.postForEntity("http://localhost:" + port + "/api/lights", entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(answer.getBody());
        System.out.println(root);
        int id = root.get("id").intValue();

        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:" + port + "/api/lights/" + id, String.class);
        mapper = new ObjectMapper();
        root = mapper.readTree(response.getBody());
        int level = root.get("level").intValue();
        String status = root.path("status").textValue();
        int roomId = root.path("roomId").intValue();
        Assertions.assertThat(level).isEqualTo(2);
        Assertions.assertThat(status).isEqualTo("ON");
        Assertions.assertThat(roomId).isEqualTo(-9);
    }
}
