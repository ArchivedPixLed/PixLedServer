package com.esme.spring.pixledserver.mqtt;

import com.esme.spring.pixledserver.model.light.Light;
import com.esme.spring.pixledserver.model.light.dao.LightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MqttConnectionHandler {
    @Autowired
    private LightDao lightDao;

    public void handle(Message<?> message) {
        Long id = Long.valueOf(message.getPayload().toString());
        System.out.println("Light " + id + " : " + message.getHeaders().get("mqtt_receivedTopic"));
        System.out.println(lightDao == null);
        Light light = lightDao.findById(id).orElse(null);
    }
}
