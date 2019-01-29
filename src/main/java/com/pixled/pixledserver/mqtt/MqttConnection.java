package com.pixled.pixledserver.mqtt;

import com.pixled.pixledserver.core.ToggleState;

public interface MqttConnection {

    String connected_topic = "/connected";
    String disconnected_topic = "/disconnected";
    String check_topic = "/check";

    void connect();
    void checkConnectedModule();
    void publishDeviceColor(int deviceId, String color);
    void publishDeviceSwitch(int deviceId, ToggleState status);
    void publishGroupSwitch(int groupId, ToggleState status);
}
