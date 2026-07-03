package com.wildsight.backend.controller;

import com.wildsight.backend.dto.PopulationEstimateRequest;
import com.wildsight.backend.dto.PopulationEstimateResponse;
import com.wildsight.backend.service.PopulationEstimateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/population-estimates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PopulationEstimateController {

    private final PopulationEstimateService populationEstimateService;

    @PostMapping
    public PopulationEstimateResponse createEstimate(
            @Valid @RequestBody PopulationEstimateRequest request) {

        return populationEstimateService.createEstimate(request);
    }

    @GetMapping
    public List<PopulationEstimateResponse> getAllEstimates() {

        return populationEstimateService.getAllEstimates();
    }

    @GetMapping("/{id}")
    public PopulationEstimateResponse getEstimateById(
            @PathVariable Long id) {

        return populationEstimateService.getEstimateById(id);
    }

    @PutMapping("/{id}")
    public PopulationEstimateResponse updateEstimate(
            @PathVariable Long id,
            @Valid @RequestBody PopulationEstimateRequest request) {

        return populationEstimateService.updateEstimate(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteEstimate(
            @PathVariable Long id) {

        populationEstimateService.deleteEstimate(id);

        return "Population Estimate deleted successfully";
    }
}