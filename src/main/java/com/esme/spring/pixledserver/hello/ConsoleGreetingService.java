package com.esme.spring.pixledserver.hello;

import org.springframework.stereotype.Service;

@Service
// @Qualifier("Primary")
public class ConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
