package com.manoj.fitnessTracker.services.stats;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manoj.fitnessTracker.dto.GraphDTO;
import com.manoj.fitnessTracker.dto.StatsDTO;
import com.manoj.fitnessTracker.model.Activity;
import com.manoj.fitnessTracker.model.Workout;
import com.manoj.fitnessTracker.repository.ActivityRepo;
import com.manoj.fitnessTracker.repository.GoalRepo;
import com.manoj.fitnessTracker.repository.WorkoutRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    // Goal Repo to gets Achieved Stats
    private final GoalRepo goalRepo;
    // Activity Repo to gets setps, dist.. Stats
    private final ActivityRepo activityRepo;
    // Workout Repo to gets duration Stats
    private final WorkoutRepo workoutRepo;

    public StatsDTO getStats() {

        Long achievedGoals = goalRepo.countAchievedGoals();
        Long notAchievedGoals = goalRepo.countNotAchievedGoals();

        Integer totalSteps = activityRepo.getTotalSteps();
        Double totalDistance = activityRepo.getTotalDistance();
        Integer totalActivityCaloriesBurned = activityRepo.getTotalActivityCaloriesBurned();

        Integer totalDuration = workoutRepo.getTotalDuration();
        Integer totalWorkoutCaloriesBurned = workoutRepo.getTotalWorkoutCaloriesBurned();
        
        int totalCaloriesBurned = (totalActivityCaloriesBurned != null ? totalActivityCaloriesBurned : 0) + 
                        (totalWorkoutCaloriesBurned != null ? totalWorkoutCaloriesBurned : 0);

        StatsDTO statsdto = new StatsDTO();
        // If achievedGoals is not null, it uses the value of achievedGoals.
        // If achievedGoals is null, it assigns 0.
        statsdto.setAchievedGoals(achievedGoals != null ? achievedGoals : 0);
        statsdto.setNotAchievedGoals(notAchievedGoals != null ? notAchievedGoals : 0);

        statsdto.setSteps(totalSteps != null ? totalSteps : 0);
        statsdto.setDistance(totalDistance != null ? totalDistance : 0.0);
        statsdto.setTotalCaloriesBurned(totalCaloriesBurned);

        statsdto.setDuration(totalDuration != null ? totalDuration : 0);

        return statsdto;
    }

    public GraphDTO getGraphStats() {
        // .of(page_no, page_size)
        Pageable pageable = PageRequest.of(0, 7);

        List<Workout> workouts = workoutRepo.findLast7Workouts(pageable);

        List<Activity> activities = activityRepo.findLast7Activities(pageable);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setWorkoutDTO(workouts.stream().map(Workout::getWorkoutDTO).collect(Collectors.toList()));
        graphDTO.setActivityDTO(activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList()));

        return graphDTO;

    }

    
}
