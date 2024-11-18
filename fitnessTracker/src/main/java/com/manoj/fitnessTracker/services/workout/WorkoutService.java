package com.manoj.fitnessTracker.services.workout;

import java.util.List;

import com.manoj.fitnessTracker.dto.WorkoutDTO;

public interface WorkoutService {

    WorkoutDTO postWorkoutDTO(WorkoutDTO workoutdto);

    List<WorkoutDTO> getWorkoutDTO();
    
}
