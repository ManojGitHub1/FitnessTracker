package com.manoj.fitnessTracker.services.goal;

import java.util.List;

import com.manoj.fitnessTracker.dto.GoalDTO;

public interface GoalService {
    
    GoalDTO postGoalDTO(GoalDTO goalDTO);

    public List<GoalDTO> getGoalDTO();

    public GoalDTO updateStatus(Long id);

}
