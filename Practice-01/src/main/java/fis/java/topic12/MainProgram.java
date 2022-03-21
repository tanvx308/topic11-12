package fis.java.topic12;

import fis.java.topic12.bean.MyComponent;
import fis.java.topic12.config.AppConfiguration;
import fis.java.topic12.domain.Language;
import fis.java.topic12.service.GreetingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainProgram {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Language language = (Language) context.getBean("language");

        System.out.println("Bean Language: "+ language);
        System.out.println("Call language.sayBye(): "+ language.getBye());

        GreetingService service = (GreetingService) context.getBean("greetingService");


        service.sayGreeting();

        System.out.println("----------");

        MyComponent myComponent = (MyComponent) context.getBean("myComponent");

        myComponent.showAppInfo();

    }
}
