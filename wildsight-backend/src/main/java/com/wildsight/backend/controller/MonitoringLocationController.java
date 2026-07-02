package com.wildsight.backend.controller;

import com.wildsight.backend.dto.MonitoringLocationRequest;
import com.wildsight.backend.dto.MonitoringLocationResponse;
import com.wildsight.backend.service.MonitoringLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring-locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MonitoringLocationController {

    private final MonitoringLocationService monitoringLocationService;

    @PostMapping
    public MonitoringLocationResponse createLocation(
            @Valid @RequestBody MonitoringLocationRequest request) {

        return monitoringLocationService.createLocation(request);
    }

    @GetMapping
    public List<MonitoringLocationResponse> getAllLocations() {

        return monitoringLocationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public MonitoringLocationResponse getLocationById(
            @PathVariable Long id) {

        return monitoringLocationService.getLocationById(id);
    }

    @PutMapping("/{id}")
    public MonitoringLocationResponse updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody MonitoringLocationRequest request) {

        return monitoringLocationService.updateLocation(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable Long id) {

        monitoringLocationService.deleteLocation(id);

        return "Monitoring Location deleted successfully";
    }
}