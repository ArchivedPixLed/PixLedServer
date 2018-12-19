package com.esme.spring.pixledserver;

import com.esme.spring.pixledserver.hello.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAppConfig {

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
