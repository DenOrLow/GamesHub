package com.example.gameshub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GamesHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesHubApplication.class, args);
    }

}
