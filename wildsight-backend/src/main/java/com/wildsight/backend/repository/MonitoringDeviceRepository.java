package com.wildsight.backend.repository;

import com.wildsight.backend.entity.MonitoringDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringDeviceRepository
        extends JpaRepository<MonitoringDevice, Long> {

}