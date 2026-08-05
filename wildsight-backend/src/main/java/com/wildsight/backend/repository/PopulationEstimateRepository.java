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
    // Total Population
    // ===============================

    @Query("""
            SELECT COALESCE(SUM(p.estimatedPopulation),0)
            FROM PopulationEstimate p
            """)
    Long getTotalEstimatedPopulation();



    // ===============================
    // Average Density
    // ===============================

    @Query("""
            SELECT COALESCE(AVG(p.density),0)
            FROM PopulationEstimate p
            """)
    BigDecimal getAverageDensity();



    // ===============================
    // Average Growth Rate
    // ===============================

    @Query("""
            SELECT COALESCE(AVG(p.growthRate),0)
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
    // Species Distribution
    // ===============================

    @Query("""
            SELECT p
            FROM PopulationEstimate p
            JOIN FETCH p.species
            JOIN FETCH p.survey
            ORDER BY p.estimatedPopulation DESC
            """)
    List<PopulationEstimate> getSpeciesDistribution();





    // ===============================
    // Migration Analysis
    // ===============================

    @Query("""
            SELECT p
            FROM PopulationEstimate p
            JOIN FETCH p.species
            JOIN FETCH p.survey
            WHERE p.migrationPattern IS NOT NULL
            """)
    List<PopulationEstimate> getMigrationPatterns();


}