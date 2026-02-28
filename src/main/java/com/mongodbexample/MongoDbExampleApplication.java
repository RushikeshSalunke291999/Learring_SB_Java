package com.mongodbexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MongoDbExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbExampleApplication.class, args);
    }

}
