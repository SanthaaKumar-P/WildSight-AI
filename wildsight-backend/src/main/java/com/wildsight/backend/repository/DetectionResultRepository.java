package com.wildsight.backend.repository;


import com.wildsight.backend.entity.DetectionResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;



@Repository
public interface DetectionResultRepository 
        extends JpaRepository<DetectionResult, Long> {



    // Count by category
    long countByCategory(String category);



    // Count endangered species
    long countByConservationStatus(String status);



    // Total unique species
    @Query("""
            SELECT COUNT(DISTINCT d.species)
            FROM DetectionResult d
            """)
    long countTotalSpecies();




    // Species distribution chart

    @Query("""
            SELECT d.species, COUNT(d)
            FROM DetectionResult d
            GROUP BY d.species
            ORDER BY COUNT(d) DESC
            """)
    List<Object[]> getSpeciesDistribution();





    // Category distribution chart

    @Query("""
            SELECT d.category, COUNT(d)
            FROM DetectionResult d
            GROUP BY d.category
            """)
    List<Object[]> getCategoryDistribution();



}