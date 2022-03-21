package fis.java.topic12.bean;

import fis.java.topic12.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    @Autowired
    private MyRepository repository;

    public void showAppInfo(){
        System.out.println("Now is: " + repository.getSystemDateTime());
        System.out.println("App name: "  + repository.getAppName());
    }
}
