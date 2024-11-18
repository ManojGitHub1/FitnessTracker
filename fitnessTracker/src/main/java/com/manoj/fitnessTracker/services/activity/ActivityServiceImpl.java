package com.manoj.fitnessTracker.services.activity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.manoj.fitnessTracker.dto.ActivityDTO;
import com.manoj.fitnessTracker.model.Activity;

import lombok.RequiredArgsConstructor;
import com.manoj.fitnessTracker.repository.ActivityRepo;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepo activityRepo;

    public ActivityDTO postActivityDTO(ActivityDTO activitydto) {
        Activity activity = new Activity();
        activity.setDate(activitydto.getDate());
        activity.setSteps(activitydto.getSteps());
        activity.setDistance(activitydto.getDistance());
        activity.setCaloriesBurned(activitydto.getCaloriesBurned());
        return activityRepo.save(activity).getActivityDTO();
    }

    public List<ActivityDTO> getActivityDTO(){
        List<Activity> activities = activityRepo.findAll();
        return activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList());
    }

}
 