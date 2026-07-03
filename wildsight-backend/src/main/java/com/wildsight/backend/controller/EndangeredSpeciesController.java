package com.wildsight.backend.controller;

import com.wildsight.backend.dto.EndangeredSpeciesRequest;
import com.wildsight.backend.dto.EndangeredSpeciesResponse;
import com.wildsight.backend.service.EndangeredSpeciesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endangered-species")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EndangeredSpeciesController {

    private final EndangeredSpeciesService endangeredSpeciesService;

    @PostMapping
    public EndangeredSpeciesResponse createEndangeredSpecies(
            @Valid @RequestBody EndangeredSpeciesRequest request) {

        return endangeredSpeciesService.createEndangeredSpecies(request);
    }

    @GetMapping
    public List<EndangeredSpeciesResponse> getAllEndangeredSpecies() {

        return endangeredSpeciesService.getAllEndangeredSpecies();
    }

    @GetMapping("/{id}")
    public EndangeredSpeciesResponse getEndangeredSpeciesById(
            @PathVariable Long id) {

        return endangeredSpeciesService.getEndangeredSpeciesById(id);
    }

    @PutMapping("/{id}")
    public EndangeredSpeciesResponse updateEndangeredSpecies(
            @PathVariable Long id,
            @Valid @RequestBody EndangeredSpeciesRequest request) {

        return endangeredSpeciesService.updateEndangeredSpecies(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteEndangeredSpecies(
            @PathVariable Long id) {

        endangeredSpeciesService.deleteEndangeredSpecies(id);

        return "Endangered Species deleted successfully";
    }
}