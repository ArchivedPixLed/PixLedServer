package com.pixled.pixledserver.config;

import com.pixled.pixledserver.mqtt.MqttConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAppConfig {

    Logger logger = LoggerFactory.getLogger(MainAppConfig.class);

    @Autowired
    public MainAppConfig(MqttConnection mqttConnection) {
        // logger.info("MQTT Configuration");
        // mqttConnection.connect();
    }

}
