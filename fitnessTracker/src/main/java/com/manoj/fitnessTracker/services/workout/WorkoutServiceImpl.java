package com.manoj.fitnessTracker.services.workout;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manoj.fitnessTracker.dto.WorkoutDTO;
import com.manoj.fitnessTracker.model.Workout;
import com.manoj.fitnessTracker.repository.WorkoutRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepo workoutRepo;

    public WorkoutDTO postWorkoutDTO(WorkoutDTO workoutdto) {
        Workout workout = new Workout();
        workout.setDate(workoutdto.getDate());
        workout.setType(workoutdto.getType());
        workout.setDuration(workoutdto.getDuration());
        workout.setCaloriesBurned(workoutdto.getCaloriesBurned());

        return workoutRepo.save(workout).getWorkoutDTO();
    }

    public List<WorkoutDTO> getWorkoutDTO(){
        List<Workout> workouts = workoutRepo.findAll();
        return workouts.stream().map(Workout::getWorkoutDTO).collect(Collectors.toList());
    }

}
