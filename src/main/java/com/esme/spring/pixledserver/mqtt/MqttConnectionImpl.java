package com.esme.spring.pixledserver.mqtt;

import com.esme.spring.pixledserver.MainAppConfig;
import com.esme.spring.pixledserver.model.Status;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttConnectionImpl implements MqttConnection {

    Logger logger = LoggerFactory.getLogger(MainAppConfig.class);

    @Autowired
    private MqttConnectionHandler mqttConnectionHandler;

    private IMqttClient client;
    private static final String clientId = "PixLedServer";

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
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        try {
            client.subscribe(connected_topic, 1, mqttConnectionHandler);
            client.subscribe(disconnected_topic, 1, mqttConnectionHandler);
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
        try {
            client.publish(check_topic, msg);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publishColor(long buildingId, long roomId, long lightId, String color) {
        if (client.isConnected()) {
            String topic = "/buildings/" + buildingId + "/rooms/" + roomId + "/lights/" + lightId + "/color";
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
    public void publishSwitch(long buildingId, long roomId, long lightId, Status status) {
        if (client.isConnected()) {
            String topic = "/buildings/" + buildingId + "/rooms/" + roomId + "/lights/" + lightId + "/switch";
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
