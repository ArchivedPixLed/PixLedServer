package com.esme.spring.pixledserver;

import com.esme.spring.pixledserver.hello.GreetingService;
import com.esme.spring.pixledserver.model.light.dao.LightDao;
import com.esme.spring.pixledserver.mqtt.MqttConnection;
import com.esme.spring.pixledserver.mqtt.MqttConnectionHandler;
import com.esme.spring.pixledserver.mqtt.MqttConnectionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
public class MainAppConfig {

    Logger logger = LoggerFactory.getLogger(MainAppConfig.class);


    @Autowired
    public MainAppConfig(MqttConnection mqttConnection) {
        logger.info("MQTT Configuration");
        mqttConnection.connect();
    }

    @Bean
    public CommandLineRunner greetingCommandLine(@Qualifier("Another") GreetingService greetingService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                greetingService.greet("Spring");
            }
        };
    }

}
