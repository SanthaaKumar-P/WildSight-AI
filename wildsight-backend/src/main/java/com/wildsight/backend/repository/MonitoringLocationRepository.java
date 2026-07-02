package com.wildsight.backend.repository;

import com.wildsight.backend.entity.MonitoringLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLocationRepository
        extends JpaRepository<MonitoringLocation, Long> {
}