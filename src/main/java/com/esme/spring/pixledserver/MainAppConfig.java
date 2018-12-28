package com.esme.spring.pixledserver;

import com.esme.spring.pixledserver.hello.GreetingService;
import com.esme.spring.pixledserver.model.light.dao.LightDao;
import com.esme.spring.pixledserver.mqtt.MqttConnection;
import com.esme.spring.pixledserver.mqtt.MqttConnectionHandler;
import com.esme.spring.pixledserver.mqtt.MqttConnectionImpl;
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



    @Autowired
    public MainAppConfig(MqttConnection mqttConnection) {
        System.out.println("Instantiating config");
        System.out.println(mqttConnection == null);
        mqttConnection.connect();
    }

//    @Autowired
//    private MqttConnectionHandler mqttConnectionHandler;

//    @Bean
//    public MessageChannel mqttInputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageProducer inbound() {
//        MqttPahoMessageDrivenChannelAdapter adapter =
//                new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883", "PixLedServer",
//                        "module_connected");
//        adapter.setCompletionTimeout(5000);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(1);
//        adapter.setOutputChannel(mqttInputChannel());
//        return adapter;
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler handler() {
//        return new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                System.out.println(message.getPayload());
//                mqttConnectionHandler.handle(message);
//            }
//
//        };
//    }

//    @Bean
//    public MqttConnection mqttConnection() {
//        MqttConnection mqttConnection = new MqttConnectionImpl();
//        mqttConnection.connect();
//        return mqttConnection;
//    }

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
