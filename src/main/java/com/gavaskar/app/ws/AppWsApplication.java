package com.gavaskar.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.gavaskar.app.ws.io.repository")
public class AppWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWsApplication.class);
    }
}
