package com.wildsight.backend.repository;


import com.wildsight.backend.entity.PopulationEstimate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // ================= LOCATION BASED POPULATION =================


@Query("""
SELECT COALESCE(SUM(p.estimatedPopulation),0)
FROM PopulationEstimate p
WHERE p.survey.location.locationId = :locationId
""")
Long getPopulationByLocation(
        @Param("locationId") Long locationId
);



@Query("""
SELECT COUNT(DISTINCT p.species.speciesId)
FROM PopulationEstimate p
WHERE p.survey.location.locationId = :locationId
""")
Long getSpeciesCountByLocation(
        @Param("locationId") Long locationId
);



@Query("""
SELECT p
FROM PopulationEstimate p
JOIN FETCH p.species
JOIN FETCH p.survey
WHERE p.survey.location.locationId = :locationId
ORDER BY p.estimatedPopulation DESC
""")
List<PopulationEstimate> getSpeciesDistributionByLocation(
        @Param("locationId") Long locationId
);



@Query("""
SELECT AVG(p.density)
FROM PopulationEstimate p
WHERE p.survey.location.locationId = :locationId
""")
BigDecimal getAverageDensityByLocation(
        @Param("locationId") Long locationId
);



@Query("""
SELECT AVG(p.growthRate)
FROM PopulationEstimate p
WHERE p.survey.location.locationId = :locationId
""")
BigDecimal getGrowthRateByLocation(
        @Param("locationId") Long locationId
);
}