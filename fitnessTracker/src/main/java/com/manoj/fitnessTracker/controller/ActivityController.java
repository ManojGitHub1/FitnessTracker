package com.manoj.fitnessTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.fitnessTracker.dto.ActivityDTO;
import com.manoj.fitnessTracker.services.activity.ActivityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivityController {
    
    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO createActivity = activityService.postActivityDTO(activityDTO);
        
        if( createActivity != null ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create activity");
        }
    }

    @GetMapping("/activities")
    public ResponseEntity<?> getActivity() {
        try{
            return ResponseEntity.ok(activityService.getActivityDTO());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get activity");
        }
    }

}
