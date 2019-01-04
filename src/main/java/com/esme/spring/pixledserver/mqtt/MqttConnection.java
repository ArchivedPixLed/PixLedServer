package com.esme.spring.pixledserver.mqtt;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.light.Light;
import org.springframework.stereotype.Component;

public interface MqttConnection {

    String connected_topic = "/connected";
    String disconnected_topic = "/disconnected";
    String check_topic = "/check";

    void connect();
    void checkConnectedModule();
    void publishColor(long buildingId, long roomId, long lightId, String color);
    void publishSwitch(long buildingId, long roomId, long lightId, Status status);
}
