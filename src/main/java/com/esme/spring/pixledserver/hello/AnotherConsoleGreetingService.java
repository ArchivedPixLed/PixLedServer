package com.esme.spring.pixledserver.hello;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Another")
public class AnotherConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Bonjour, " + name + "!");
    }
}
