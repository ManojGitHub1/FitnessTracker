package com.manoj.fitnessTracker.services.activity;


import java.util.List;

import com.manoj.fitnessTracker.dto.ActivityDTO;


public interface ActivityService {
    
    ActivityDTO postActivityDTO(ActivityDTO dto);

    List<ActivityDTO> getActivityDTO();

}
