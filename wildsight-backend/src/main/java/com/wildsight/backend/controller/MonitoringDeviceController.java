package com.wildsight.backend.controller;

import com.wildsight.backend.dto.MonitoringDeviceRequest;
import com.wildsight.backend.dto.MonitoringDeviceResponse;
import com.wildsight.backend.service.MonitoringDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/monitoring-devices")
@RequiredArgsConstructor
@Tag(
    name = "Monitoring Device Management",
    description = "APIs for managing monitoring devices"
)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class MonitoringDeviceController {

    private final MonitoringDeviceService monitoringDeviceService;
    
    @Operation(summary = "Create monitoring device")
    @PostMapping
    public MonitoringDeviceResponse createDevice(
            @Valid @RequestBody MonitoringDeviceRequest request) {

        return monitoringDeviceService.createDevice(request);
    }

    @Operation(summary = "Get all monitoring devices")
    @GetMapping
    public List<MonitoringDeviceResponse> getAllDevices() {

        return monitoringDeviceService.getAllDevices();
    }

    @Operation(summary = "Get monitoring device by ID")
    @GetMapping("/{id}")
    public MonitoringDeviceResponse getDeviceById(
            @PathVariable Long id) {

        return monitoringDeviceService.getDeviceById(id);
    }

    @Operation(summary = "Update monitoring device")
    @PutMapping("/{id}")
    public MonitoringDeviceResponse updateDevice(
            @PathVariable Long id,
            @Valid @RequestBody MonitoringDeviceRequest request) {

        return monitoringDeviceService.updateDevice(id, request);
    }

    @Operation(summary = "Delete monitoring device")
    @DeleteMapping("/{id}")
    public String deleteDevice(
            @PathVariable Long id) {

        monitoringDeviceService.deleteDevice(id);

        return "Monitoring Device deleted successfully";
    }
}