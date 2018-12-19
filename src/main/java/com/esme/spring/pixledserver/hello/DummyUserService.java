package com.esme.spring.pixledserver.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DummyUserService implements UserService {

    @Autowired
    @Qualifier("Another")
    private GreetingService greetingService;


    @Override
    public void greetAll() {
        ArrayList<String> peopleToGreet = new ArrayList<>();
        peopleToGreet.add("Elodie");
        peopleToGreet.add("Charles");

        for (String people : peopleToGreet) {
            greetingService.greet(people);
        }
    }
}
