package com.prominentpixel.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FunRestController {

    // Inject custom properties for 'coach.name' and 'team.name'
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/")
    public String sayHello() {
        return "Hello world!! Time on server is : " + LocalDateTime.now();
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!!";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day!!";
    }

    // Expose new endpoint for team information
    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return "Coach : " + this.coachName + ", Team : " + this.teamName;
    }
}
