package com.prominentpixel.schedulingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Ensures that a background task executor is created. Without it, nothing gets scheduled.
public class SchedulingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingdemoApplication.class, args);
	}

}
