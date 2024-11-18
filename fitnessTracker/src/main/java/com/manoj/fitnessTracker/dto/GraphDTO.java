package com.manoj.fitnessTracker.dto;

import java.util.List;

import lombok.Data;

@Data
public class GraphDTO {

    // While sending API this is sent workoutDTO
    List<WorkoutDTO> workoutDTO;

    List<ActivityDTO> activityDTO;
    
}
