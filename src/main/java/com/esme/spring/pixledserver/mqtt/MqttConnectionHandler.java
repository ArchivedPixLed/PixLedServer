package com.esme.spring.pixledserver.mqtt;

import com.esme.spring.pixledserver.model.light.Light;
import com.esme.spring.pixledserver.model.light.dao.LightDao;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MqttConnectionHandler implements IMqttMessageListener {

    @Autowired
    private LightDao lightDao;

    @Autowired
    private MqttConnection mqttConnection;

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Long id = Long.valueOf(message.toString());
        System.out.println("Light " + id + " : " + topic);
        Light light = lightDao.findById(id).orElse(null);
        if (light != null) {
            if (topic.equals(MqttConnection.connected_topic)) {
                light.setConnected(true);
                lightDao.save(light);

                mqttConnection.publishSwitch(
                        light.getRoom().getBuilding().getId(),
                        light.getRoom().getId(),
                        light.getId(),
                        light.getStatus()
                );

                System.out.println(light.getColor().getArgb());
                mqttConnection.publishColor(
                        light.getRoom().getBuilding().getId(),
                        light.getRoom().getId(),
                        light.getId(),
                        light.getColor().getArgb().toString()
                );

            } else if (topic.equals(MqttConnection.disconnected_topic)) {
                light.setConnected(false);
            }
        }
    }
}
