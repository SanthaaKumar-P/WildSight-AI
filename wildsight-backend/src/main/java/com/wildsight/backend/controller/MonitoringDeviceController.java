package com.wildsight.backend.controller;

import com.wildsight.backend.dto.MonitoringDeviceRequest;
import com.wildsight.backend.dto.MonitoringDeviceResponse;
import com.wildsight.backend.service.MonitoringDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring-devices")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MonitoringDeviceController {

    private final MonitoringDeviceService monitoringDeviceService;

    @PostMapping
    public MonitoringDeviceResponse createDevice(
            @Valid @RequestBody MonitoringDeviceRequest request) {

        return monitoringDeviceService.createDevice(request);
    }

    @GetMapping
    public List<MonitoringDeviceResponse> getAllDevices() {

        return monitoringDeviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public MonitoringDeviceResponse getDeviceById(
            @PathVariable Long id) {

        return monitoringDeviceService.getDeviceById(id);
    }

    @PutMapping("/{id}")
    public MonitoringDeviceResponse updateDevice(
            @PathVariable Long id,
            @Valid @RequestBody MonitoringDeviceRequest request) {

        return monitoringDeviceService.updateDevice(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteDevice(
            @PathVariable Long id) {

        monitoringDeviceService.deleteDevice(id);

        return "Monitoring Device deleted successfully";
    }
}