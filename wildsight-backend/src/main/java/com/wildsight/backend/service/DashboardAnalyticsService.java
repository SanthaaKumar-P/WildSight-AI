package com.wildsight.backend.service;


import com.wildsight.backend.dto.BiodiversityDashboardResponse;
import com.wildsight.backend.dto.HabitatDashboardResponse;
import com.wildsight.backend.dto.population.PopulationDashboardResponse;


public interface DashboardAnalyticsService {


    PopulationDashboardResponse getPopulationAnalytics();


    BiodiversityDashboardResponse getBiodiversityAnalytics();


    HabitatDashboardResponse getHabitatAnalytics();


}