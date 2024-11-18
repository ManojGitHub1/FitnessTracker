package com.manoj.fitnessTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.fitnessTracker.dto.GraphDTO;
import com.manoj.fitnessTracker.services.stats.StatsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {

        return ResponseEntity.ok(statsService.getStats());

    }

    @GetMapping("/graphs")
    public ResponseEntity<?> getGraphStats() {
        GraphDTO graphDTO = statsService.getGraphStats();

        if(graphDTO != null) {
            return ResponseEntity.ok(graphDTO);
        }else {
            return ResponseEntity.status(404).body(null);
        }
    }
    
}
