package com.manoj.fitnessTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.manoj.fitnessTracker", "services.activity", "repository"})
public class FitnessTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTrackerApplication.class, args);
    }
}
