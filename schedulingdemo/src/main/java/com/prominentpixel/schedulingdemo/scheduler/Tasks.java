package com.prominentpixel.schedulingdemo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class Tasks {

    private static Logger logger = Logger.getLogger(Tasks.class.getName());
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /*
        fixedRate attribute specifies interval between method invocations.
     */
    @Scheduled(fixedRate = 5000) // Or use @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void reportCurrentTime() {
        logger.info("Time is now : " + dateFormat.format(new Date()));
    }

}
