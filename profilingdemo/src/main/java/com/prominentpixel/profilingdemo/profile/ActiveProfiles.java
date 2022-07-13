package com.prominentpixel.profilingdemo.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

// Displays all the active profiles
@Component
@Order(1)
public class ActiveProfiles implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(ActiveProfiles.class.getName());

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Active profiles : " + Arrays.toString(environment.getActiveProfiles()));
    }
}
