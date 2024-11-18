package com.manoj.fitnessTracker.model;

import java.time.LocalDate;

import com.manoj.fitnessTracker.dto.WorkoutDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// @Data is a lombok annotation that generates getters, setters, toString, equals, and hashCode methods for the class.
@Entity
@Data
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private Long id;

    private String type;
    private LocalDate date;
    private int duration;
    private int caloriesBurned;

    public WorkoutDTO getWorkoutDTO() {
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO.setId(id);
        workoutDTO.setDate(date);
        workoutDTO.setType(type);
        workoutDTO.setDuration(duration);
        workoutDTO.setCaloriesBurned(caloriesBurned);

        return workoutDTO;
    }

}
