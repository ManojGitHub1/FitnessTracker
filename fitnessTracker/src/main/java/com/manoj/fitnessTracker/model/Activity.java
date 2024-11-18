package com.manoj.fitnessTracker.model;

import java.time.LocalDate;

import com.manoj.fitnessTracker.dto.ActivityDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private Long id;

    private LocalDate date;
    private int steps;
    private double distance;
    private int caloriesBurned;

    public ActivityDTO getActivityDTO() {

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(id);
        activityDTO.setDate(date);
        activityDTO.setSteps(steps);
        activityDTO.setDistance(distance);
        activityDTO.setCaloriesBurned(caloriesBurned);

        return activityDTO;
    }
}
