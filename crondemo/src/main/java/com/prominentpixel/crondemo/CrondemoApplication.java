package com.prominentpixel.crondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Ensures that a background task executor is created. Without it, nothing gets scheduled.
public class CrondemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrondemoApplication.class, args);
	}

}
