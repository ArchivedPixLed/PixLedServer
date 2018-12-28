package com.esme.spring.pixledserver;

import com.esme.spring.pixledserver.hello.GreetingService;
import com.esme.spring.pixledserver.mqtt.MqttConnection;
import com.esme.spring.pixledserver.mqtt.MqttConnectionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAppConfig {

    @Bean
    public MqttConnection mqttConnection() {
        MqttConnection mqttConnection = new MqttConnectionImpl();
        mqttConnection.connect();
        return mqttConnection;
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
