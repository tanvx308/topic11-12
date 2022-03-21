package fis.java.topic12.service;

import fis.java.topic12.domain.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class GreetingService {
    @Autowired
    private Language language;

    public void sayGreeting(){
        System.out.println("Greeting: "  + language.getGreeting());
    }
}
