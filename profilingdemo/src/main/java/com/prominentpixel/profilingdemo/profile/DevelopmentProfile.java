package com.prominentpixel.profilingdemo.profile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// This runner is executed when the 'dev' profile is active
@Component
@Order(2)
@Profile(value = "dev")
public class DevelopmentProfile implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(DevelopmentProfile.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("In development.");
    }
}
