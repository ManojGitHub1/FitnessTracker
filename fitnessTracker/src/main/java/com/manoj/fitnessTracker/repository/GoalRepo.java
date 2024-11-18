package com.manoj.fitnessTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoj.fitnessTracker.model.Goal;

@Repository
public interface GoalRepo extends JpaRepository<Goal, Long> {

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = true")
    Long countAchievedGoals();

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = false")
    Long countNotAchievedGoals();

    
}
