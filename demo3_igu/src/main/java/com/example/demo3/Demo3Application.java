package com.example.demo3;

import com.example.demo3.ui.ApplicationJFX;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Demo3Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Demo3Application.context = SpringApplication.run(Demo3Application.class);
        Application.launch(ApplicationJFX.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}