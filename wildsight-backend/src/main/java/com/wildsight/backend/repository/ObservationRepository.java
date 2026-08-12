package com.wildsight.backend.repository;


import com.wildsight.backend.entity.Observation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ObservationRepository 
extends JpaRepository<Observation,Long>{



@Query("""
SELECT o
FROM Observation o
JOIN FETCH o.survey
JOIN FETCH o.species
JOIN FETCH o.location
JOIN FETCH o.device
""")
List<Observation> findAllWithDetails();


}