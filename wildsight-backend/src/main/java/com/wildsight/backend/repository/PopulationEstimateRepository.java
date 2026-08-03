package com.wildsight.backend.repository;

import com.wildsight.backend.entity.PopulationEstimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PopulationEstimateRepository
        extends JpaRepository<PopulationEstimate, Long> {

    @Query("SELECT AVG(p.growthRate) FROM PopulationEstimate p")
    BigDecimal getAverageGrowthRate();

    @Query("SELECT AVG(p.density) FROM PopulationEstimate p")
    BigDecimal getAverageDensity();

    @Query("SELECT SUM(p.estimatedPopulation) FROM PopulationEstimate p")
    Long getTotalEstimatedPopulation();

}