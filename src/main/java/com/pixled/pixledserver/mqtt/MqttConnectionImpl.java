package com.pixled.pixledserver.mqtt;

import com.pixled.pixledserver.config.MainAppConfig;
import com.pixled.pixledserver.core.ToggleState;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttConnectionImpl implements MqttConnection {

    Logger logger = LoggerFactory.getLogger(MainAppConfig.class);

    private IMqttClient client;
    private static final String clientId = "PixLedServer";

    @Autowired
    private MqttConnectionHandler mqttConnectionHandler;

    public MqttConnectionImpl() {
    }

    @Override
    public void connect() {
        logger.info("Connecting to mqtt broker...");
        try {
            client = new MqttClient("tcp://localhost:1883", clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            client.setCallback(new MqttConnectionCallback(client, this, mqttConnectionHandler));
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkConnectedModule() {
        logger.info("Checking connected modules");
        MqttMessage msg = new MqttMessage();
        msg.setQos(1);
        msg.setRetained(false);
        if (client != null && client.isConnected()) {
            try {
                client.publish(check_topic, msg);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void publishDeviceColor(int deviceId, String color) {
        if (client != null && client.isConnected()) {
            String topic = "/devices/" + deviceId + "/state/color";
            byte[] payload = color.getBytes();
            logger.info("Publish " + payload + " to " + topic);
            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(1);
            msg.setRetained(true);
            try {
                client.publish(topic, msg);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void publishDeviceSwitch(int deviceId, ToggleState status) {
        if (client != null && client.isConnected()) {
            String topic = "/devices/" + deviceId + "/state/switch";
            byte[] payload = status.name().getBytes();
            logger.info("Publish " + status.name() + " to " + topic);
            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(1);
            msg.setRetained(true);
            try {
                client.publish(topic, msg);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void publishGroupSwitch(int groupId, ToggleState status) {
        if (client != null && client.isConnected()) {
            String topic = "/devices/" + groupId + "/state/switch";
            byte[] payload = status.name().getBytes();
            logger.info("Publish " + status.name() + " to " + topic);
            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(1);
            msg.setRetained(true);
            try {
                client.publish(topic, msg);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
