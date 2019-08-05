package com.joaodurante.springproject.config;

import com.joaodurante.springproject.services.DBService;
import com.joaodurante.springproject.services.EmailService;
import com.joaodurante.springproject.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if(strategy.equals("create") || strategy.equals("create-drop")){
            dbService.instantiateTestDatabase();
            return true;
        }else
            return false;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
