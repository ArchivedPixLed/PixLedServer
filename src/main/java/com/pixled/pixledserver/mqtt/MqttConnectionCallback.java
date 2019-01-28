package com.pixled.pixledserver.mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class MqttConnectionCallback implements MqttCallbackExtended {

    private IMqttClient client;
    private MqttConnection mqttConnection;
    private MqttConnectionHandler mqttConnectionHandler;

    public MqttConnectionCallback(IMqttClient client, MqttConnection mqttConnection, MqttConnectionHandler mqttConnectionHandler) {
        this.client = client;
        this.mqttConnection = mqttConnection;
        this.mqttConnectionHandler = mqttConnectionHandler;
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        try {
             mqttConnection.checkConnectedModule();
             client.subscribe(MqttConnection.connected_topic, 1, mqttConnectionHandler);
             client.subscribe(MqttConnection.disconnected_topic, 1, mqttConnectionHandler);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
