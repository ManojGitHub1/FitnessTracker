package com.manoj.fitnessTracker.dto;

import lombok.Data;

@Data
public class StatsDTO {

    // Goal
    private long achievedGoals;
    private long notAchievedGoals;

    // Activity
    private int steps;
    private Double distance;

    // Workout
    private int duration;

    // Common for Activity and Workout
    private int totalCaloriesBurned;


}
