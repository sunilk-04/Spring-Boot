package com.prominentpixel.profilingdemo.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// This runner is executed when either 'dev' or 'prod' or both profiles are active
// Message output depends on which profile was loaded last.
// Here 'prod' profile message will be displayed because the 'prod' profile was specified after the 'dev'.
@Component
@Profile(value = {"dev", "prod"})
@Order(6)
public class DevelopmentOrProductionOrBoth implements CommandLineRunner {

    private static Logger logger = Logger.getLogger(DevelopmentOrProductionOrBoth.class.getName());
    @Value("${message}")
    private String message;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Message : " + message);
    }
}
