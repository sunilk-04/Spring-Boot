package com.prominentpixel.crondemo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class Tasks {

    private static Logger logger = Logger.getLogger(Tasks.class.getName());
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/5 * 8-11 * * *") // Runs every 5 seconds between 8AM and 11AM
    public void reportCurrentTime() {
        logger.info("Time is now : " + dateFormat.format(new Date()));
    }

}