package com.manoj.fitnessTracker.services.stats;

import com.manoj.fitnessTracker.dto.GraphDTO;
import com.manoj.fitnessTracker.dto.StatsDTO;

public interface StatsService {
    
    StatsDTO getStats();

    GraphDTO getGraphStats();

}
