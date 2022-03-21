package fis.java.topic12.config;

import fis.java.topic12.domain.Language;
import fis.java.topic12.domain.Vietnamese;
import org.springframework.context.annotation.Bean;

public class AppConfiguration {
    @Bean(name = "language")
    public Language getLanguage(){
        return new Vietnamese();
    }
}
