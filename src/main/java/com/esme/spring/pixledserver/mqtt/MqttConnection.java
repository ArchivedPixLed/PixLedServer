package com.esme.spring.pixledserver.mqtt;

import com.esme.spring.pixledserver.model.Status;
import com.esme.spring.pixledserver.model.light.Light;

public interface MqttConnection {

    void connect();
    void publishColor(long buildingId, long roomId, long lightId, String color);
    void publishSwitch(long buildingId, long roomId, long lightId, Status status);
}
