package com.wildsight.backend.controller;

import com.wildsight.backend.dto.ObservationRequest;
import com.wildsight.backend.dto.ObservationResponse;
import com.wildsight.backend.service.ObservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ObservationController {

    private final ObservationService observationService;

    @PostMapping
    public ObservationResponse createObservation(
            @Valid @RequestBody ObservationRequest request) {

        return observationService.createObservation(request);
    }

    @GetMapping
    public List<ObservationResponse> getAllObservations() {

        return observationService.getAllObservations();
    }

    @GetMapping("/{id}")
    public ObservationResponse getObservationById(
            @PathVariable Long id) {

        return observationService.getObservationById(id);
    }

    @PutMapping("/{id}")
    public ObservationResponse updateObservation(
            @PathVariable Long id,
            @Valid @RequestBody ObservationRequest request) {

        return observationService.updateObservation(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteObservation(
            @PathVariable Long id) {

        observationService.deleteObservation(id);

        return "Observation deleted successfully";
    }
}
