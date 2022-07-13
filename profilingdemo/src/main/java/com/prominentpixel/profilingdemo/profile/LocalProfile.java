package com.prominentpixel.profilingdemo.profile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// This runner is executed when the 'local' profile is active
@Component
@Profile(value = "local") // @Profile determines which runner beans are registered
@Order(4)
public class LocalProfile implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(LocalProfile.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("In local.");
    }
}
