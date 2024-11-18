package com.manoj.fitnessTracker.services.goal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.manoj.fitnessTracker.dto.GoalDTO;
import com.manoj.fitnessTracker.model.Goal;
import com.manoj.fitnessTracker.repository.GoalRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepo goalRepo;

    public GoalDTO postGoalDTO(GoalDTO goalDTO){
        Goal goal = new Goal();

        goal.setDescription(goalDTO.getDescription());
        goal.setStartDate(goalDTO.getStartDate());;
        goal.setEndDate(goalDTO.getEndDate());
        goal.setAchieved(false);

        return goalRepo.save(goal).getGoalDTO();
    }

    public List<GoalDTO> getGoalDTO(){
        List<Goal> goals = goalRepo.findAll();
        return goals.stream().map(Goal::getGoalDTO).collect(Collectors.toList());
    }

    public GoalDTO updateStatus(Long id) {
        Optional<Goal> optionalGoal = goalRepo.findById(id);

        if(optionalGoal.isPresent()) {
            Goal existingGoal = optionalGoal.get();

            existingGoal.setAchieved(true);
            return goalRepo.save(existingGoal).getGoalDTO();
        }

        throw new EntityNotFoundException("Goal not found");
    }

}
