package com.wildsight.backend.repository;


import com.wildsight.backend.entity.PopulationEstimate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;



@Repository
public interface PopulationEstimateRepository
        extends JpaRepository<PopulationEstimate, Long> {



    // ===============================
    // Population Metrics
    // ===============================


    @Query("""
            SELECT SUM(p.estimatedPopulation)
            FROM PopulationEstimate p
            """)
    Long getTotalEstimatedPopulation();



    @Query("""
            SELECT AVG(p.density)
            FROM PopulationEstimate p
            """)
    BigDecimal getAverageDensity();



    @Query("""
            SELECT AVG(p.growthRate)
            FROM PopulationEstimate p
            """)
    BigDecimal getAverageGrowthRate();




    // ===============================
    // Species Richness
    // ===============================


    @Query("""
            SELECT COUNT(DISTINCT p.species.speciesId)
            FROM PopulationEstimate p
            """)
    Long getSpeciesCount();




    // ===============================
    // Species Distribution Mapping
    // ===============================


    @Query("""
            SELECT p
            FROM PopulationEstimate p
            ORDER BY p.estimatedPopulation DESC
            """)
    List<PopulationEstimate> getSpeciesDistribution();




    // ===============================
    // Migration Analysis
    // ===============================


    @Query("""
            SELECT p
            FROM PopulationEstimate p
            WHERE p.migrationPattern IS NOT NULL
            """)
    List<PopulationEstimate> getMigrationPatterns();



}