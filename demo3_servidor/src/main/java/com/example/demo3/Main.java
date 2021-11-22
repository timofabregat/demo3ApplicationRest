package com.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Main.context = SpringApplication.run(Main.class);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}
