package com.wildsight.backend.repository;

import com.wildsight.backend.entity.PopulationEstimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationEstimateRepository
        extends JpaRepository<PopulationEstimate, Long> {

}