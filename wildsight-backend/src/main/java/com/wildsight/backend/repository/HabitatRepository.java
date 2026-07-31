package com.wildsight.backend.repository;

import com.wildsight.backend.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {

    @Query("SELECT AVG(h.habitatQualityScore) FROM Habitat h")
    Double getAverageHabitatQuality();

    @Query("SELECT AVG(h.suitabilityScore) FROM Habitat h")
    Double getAverageSuitability();

    @Query("""
            SELECT COUNT(h)
            FROM Habitat h
            WHERE h.habitatQualityScore >= 75
            """)
    Long countHealthyHabitats();

    @Query("""
            SELECT COUNT(h)
            FROM Habitat h
            WHERE h.habitatQualityScore < 75
            AND h.habitatQualityScore >= 50
            """)
    Long countDegradedHabitats();

    @Query("""
            SELECT COUNT(h)
            FROM Habitat h
            WHERE h.habitatQualityScore < 50
            """)
    Long countCriticalHabitats();
long countByHabitatTypeIgnoreCase(String habitatType);

   @Query("SELECT AVG(h.degradationLevel) FROM Habitat h")
Double getAverageDegradationLevel();

@Query("SELECT COUNT(h) FROM Habitat h WHERE h.degradationLevel < 25")
Long countLowRiskHabitats();

@Query("""
SELECT COUNT(h)
FROM Habitat h
WHERE h.degradationLevel >= 25
AND h.degradationLevel < 50
""")
Long countModerateRiskHabitats();

@Query("""
SELECT COUNT(h)
FROM Habitat h
WHERE h.degradationLevel >= 50
AND h.degradationLevel < 75
""")
Long countHighRiskHabitats();

@Query("""
SELECT COUNT(h)
FROM Habitat h
WHERE h.degradationLevel >= 75
""")
Long countCriticalHabitatsByDegradation();

@Query("SELECT AVG(h.vegetationDensity) FROM Habitat h")
Double getAverageVegetationDensity();

@Query(value = """
SELECT vegetation_type
FROM habitats
GROUP BY vegetation_type
ORDER BY COUNT(*) DESC
LIMIT 1
""", nativeQuery = true)
String getDominantVegetationType();

@Query("SELECT AVG(h.temperature) FROM Habitat h")
Double getAverageTemperature();

@Query("SELECT AVG(h.humidity) FROM Habitat h")
Double getAverageHumidity();

@Query("SELECT AVG(h.rainfall) FROM Habitat h")
Double getAverageRainfall();

@Query("SELECT AVG(h.waterQuality) FROM Habitat h")
Double getAverageWaterQuality();

@Query("SELECT AVG(h.airQuality) FROM Habitat h")
Double getAverageAirQuality();

@Query("SELECT AVG(h.suitabilityScore) FROM Habitat h")
Double getAverageSuitabilityScore();

@Query("""
SELECT COUNT(h)
FROM Habitat h
WHERE h.suitabilityScore >= 80
""")
Long countHighlySuitableHabitats();

@Query("""
SELECT COUNT(h)
FROM Habitat h
WHERE h.suitabilityScore >= 50
AND h.suitabilityScore < 80
""")
Long countModeratelySuitableHabitats();

@Query("""
SELECT COUNT(h)
FROM Habitat h
WHERE h.suitabilityScore < 50
""")
Long countUnsuitableHabitats();
}
