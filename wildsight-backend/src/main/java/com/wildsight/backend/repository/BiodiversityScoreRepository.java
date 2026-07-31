package com.wildsight.backend.repository;

import com.wildsight.backend.entity.BiodiversityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BiodiversityScoreRepository extends JpaRepository<BiodiversityScore, Long> {

    // Count records by health status
    long countByHealthStatus(String healthStatus);

    // Average Species Diversity Score
    @Query("SELECT AVG(b.speciesDiversityScore) FROM BiodiversityScore b")
    BigDecimal getAverageSpeciesDiversity();

    // Average Habitat Quality Score
    @Query("SELECT AVG(b.habitatQualityScore) FROM BiodiversityScore b")
    BigDecimal getAverageHabitatQuality();

    // Average Overall Biodiversity Score
    @Query("SELECT AVG(b.overallScore) FROM BiodiversityScore b")
    BigDecimal getAverageOverallScore();

    // Total Species Count
    @Query("SELECT COALESCE(SUM(b.speciesCount), 0) FROM BiodiversityScore b")
    Integer getTotalSpecies();

    @Query("""
SELECT AVG(
(b.speciesDiversityScore +
 b.habitatQualityScore +
 b.ecosystemHealthScore) / 3
)
FROM BiodiversityScore b
""")
Double calculateBiodiversityIndex();

@Query("""
SELECT AVG(b.speciesDiversityScore)
FROM BiodiversityScore b
""")
Double getAverageSpeciesDiversityScore();


@Query("""
SELECT COALESCE(SUM(b.speciesCount),0)
FROM BiodiversityScore b
""")
Integer getTotalSpeciesCount();

@Query("""
SELECT AVG(b.habitatQualityScore)
FROM BiodiversityScore b
""")
Double getAverageHabitatHealth();

@Query("""
SELECT COUNT(b)
FROM BiodiversityScore b
WHERE b.habitatQualityScore >= 75
""")
Long countHealthyHabitats();

@Query("""
SELECT COUNT(b)
FROM BiodiversityScore b
WHERE b.habitatQualityScore < 75
""")
Long countDegradedHabitats();

@Query("""
SELECT AVG(b.ecosystemHealthScore)
FROM BiodiversityScore b
""")
Double getAverageEcosystemHealth();
}