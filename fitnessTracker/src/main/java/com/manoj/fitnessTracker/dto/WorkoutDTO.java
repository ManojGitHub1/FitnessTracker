package com.manoj.fitnessTracker.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WorkoutDTO {

    private Long id;
    private String type;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate date;

    private int duration;
    private int caloriesBurned;
    
}
