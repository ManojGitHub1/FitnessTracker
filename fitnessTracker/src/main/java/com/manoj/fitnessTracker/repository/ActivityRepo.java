package com.manoj.fitnessTracker.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoj.fitnessTracker.model.Activity;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
    
    @Query("SELECT SUM(a.steps) FROM Activity a")
    Integer getTotalSteps();

    @Query("SELECT SUM(a.distance) FROM Activity a")
    Double getTotalDistance();

    @Query("SELECT SUM(a.caloriesBurned) FROM Activity a")
    Integer getTotalActivityCaloriesBurned();


    @Query("SELECT a FROM Activity a ORDER BY a.date DESC")
    List<Activity> findLast7Activities(Pageable pageable);

}