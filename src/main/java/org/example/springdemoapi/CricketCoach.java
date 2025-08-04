package org.example.springdemoapi;

import org.example.springdemoapi.Interface.Coach;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout(){
        return "Practice fast Bowling for 15 minutes!";
    }

    @Override
    public String getDailyFortune(){
        return "Get Daily Fortune!";
    }
}
