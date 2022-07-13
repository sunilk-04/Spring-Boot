package com.prominentpixel.profilingdemo.profile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// This runner is executed when the 'dev' and 'local' both profiles are active
@Component
@Profile(value = {"dev & local"})
@Order(5)
public class DevelopmentAndLocalProfile implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(DevelopmentAndLocalProfile.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("In development and local.");
    }
}
