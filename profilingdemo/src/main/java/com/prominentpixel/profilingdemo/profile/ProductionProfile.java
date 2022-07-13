package com.prominentpixel.profilingdemo.profile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// This runner is executed when the 'prod' profile is active and 'dev' is not active
@Component
@Order(3)
@Profile(value = "prod & !dev")
public class ProductionProfile implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(ProductionProfile.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("In production.");
    }
}
