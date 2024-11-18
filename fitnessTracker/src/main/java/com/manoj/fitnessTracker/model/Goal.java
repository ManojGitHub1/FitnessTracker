package com.manoj.fitnessTracker.model;

import java.time.LocalDate;

import com.manoj.fitnessTracker.dto.GoalDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private Long id;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean achieved;

    // To convert Goal Entity to GoalDTO we call this func
    public GoalDTO getGoalDTO() {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(id);
        goalDTO.setDescription(description);
        goalDTO.setStartDate(startDate);
        goalDTO.setEndDate(endDate);
        goalDTO.setAchieved(achieved);

        return goalDTO;
    }

}
