package com.manoj.fitnessTracker.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoj.fitnessTracker.model.Workout;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Long> { 
    
    @Query("SELECT SUM(w.duration) FROM Workout w")
    Integer getTotalDuration();

    @Query("SELECT SUM(w.caloriesBurned) FROM Workout w")
    Integer getTotalWorkoutCaloriesBurned();


    @Query("SELECT w FROM Workout w ORDER BY w.date DESC")
    List<Workout> findLast7Workouts(Pageable pageable);

}
