package com.pixled.pixledserver.mqtt;

import com.pixled.pixledserver.model.ToggleState;

public interface MqttConnection {

    String connected_topic = "/connected";
    String disconnected_topic = "/disconnected";
    String check_topic = "/check";

    void connect();
    void checkConnectedModule();
    void publishColor(long buildingId, long roomId, long lightId, String color);
    void publishSwitch(long buildingId, long roomId, long lightId, ToggleState status);
}
