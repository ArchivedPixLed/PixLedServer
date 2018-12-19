package com.esme.spring.pixledserver.hello;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

public class ConsoleGreetingServiceTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testGreeting() {
        ConsoleGreetingService greetingService = new ConsoleGreetingService();
        greetingService.greet("Spring");
        outputCapture.expect(Matchers.startsWith("Hello, Spring"));
    }
}
