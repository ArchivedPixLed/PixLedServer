package com.pixled.pixledserver.mqtt;

import com.pixled.pixledserver.model.device.base.Device;
import com.pixled.pixledserver.model.device.base.dao.DeviceDao;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttConnectionHandler implements IMqttMessageListener {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private MqttConnection mqttConnection;

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Integer id = Integer.valueOf(message.toString());
        System.out.println("Device " + id + " : " + topic);
        Device device = deviceDao.findById(id).orElse(null);
        if (device != null) {
            if (topic.equals(MqttConnection.connected_topic)) {
                device.getDeviceState().setConnected(true);
                deviceDao.save(device);

//                // Initialize the switch state of the device
//                mqttConnection.publishSwitch(
//                        light.getRoom().getBuilding().getId(),
//                        light.getRoom().getId(),
//                        light.getId(),
//                        light.getStatus()
//                );
//
//                //
//                System.out.println(light.getColor().getArgb());
//                mqttConnection.publishColor(
//                        light.getRoom().getBuilding().getId(),
//                        light.getRoom().getId(),
//                        light.getId(),
//                        light.getColor().getArgb().toString()
//                );

            } else if (topic.equals(MqttConnection.disconnected_topic)) {
                device.getDeviceState().setConnected(false);
            }
        }
    }
}
