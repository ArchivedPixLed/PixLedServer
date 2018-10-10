package com.esme.spring.faircorp.hello;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Qualifier("Another")
public class AnotherConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Bonjour, " + name + "!");
    }
}
