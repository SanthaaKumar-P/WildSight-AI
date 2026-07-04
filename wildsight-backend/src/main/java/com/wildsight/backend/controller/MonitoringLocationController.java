package com.wildsight.backend.controller;

import com.wildsight.backend.dto.MonitoringLocationRequest;
import com.wildsight.backend.dto.MonitoringLocationResponse;
import com.wildsight.backend.service.MonitoringLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/monitoring-locations")
@RequiredArgsConstructor
@Tag(
    name = "Monitoring Location Management",
    description = "APIs for managing monitoring locations"
)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class MonitoringLocationController {

    private final MonitoringLocationService monitoringLocationService;

    @Operation(summary = "Create monitoring location")
    @PostMapping
    public MonitoringLocationResponse createLocation(
            @Valid @RequestBody MonitoringLocationRequest request) {

        return monitoringLocationService.createLocation(request);
    }

    @Operation(summary = "Get all monitoring locations")
    @GetMapping
    public List<MonitoringLocationResponse> getAllLocations() {

        return monitoringLocationService.getAllLocations();
    }

    @Operation(summary = "Get monitoring location by ID")
    @GetMapping("/{id}")
    public MonitoringLocationResponse getLocationById(
            @PathVariable Long id) {

        return monitoringLocationService.getLocationById(id);
    }

    @Operation(summary = "Update monitoring location")
    @PutMapping("/{id}")
    public MonitoringLocationResponse updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody MonitoringLocationRequest request) {

        return monitoringLocationService.updateLocation(id, request);
    }

    @Operation(summary = "Delete monitoring location")
    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable Long id) {

        monitoringLocationService.deleteLocation(id);

        return "Monitoring Location deleted successfully";
    }
}