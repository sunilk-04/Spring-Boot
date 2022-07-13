package com.prominentpixel.profilingdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProfilingdemoApplication {

    // App environments : Dev | Test | Stage | Prod | UAT / Pre-Prod
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProfilingdemoApplication.class)
                .profiles("dev", "prod")
                .run(args);
    }

}
