package com.wildsight.backend.controller;

import com.wildsight.backend.dto.SpeciesRequest;
import com.wildsight.backend.dto.SpeciesResponse;
import com.wildsight.backend.service.SpeciesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SpeciesController {

    private final SpeciesService speciesService;

    @PostMapping
    public SpeciesResponse createSpecies(
            @Valid @RequestBody SpeciesRequest request) {

        return speciesService.createSpecies(request);
    }

    @GetMapping
    public List<SpeciesResponse> getAllSpecies() {

        return speciesService.getAllSpecies();
    }

    @GetMapping("/{id}")
    public SpeciesResponse getSpeciesById(
            @PathVariable Long id) {

        return speciesService.getSpeciesById(id);
    }

    @PutMapping("/{id}")
    public SpeciesResponse updateSpecies(
            @PathVariable Long id,
            @Valid @RequestBody SpeciesRequest request) {

        return speciesService.updateSpecies(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteSpecies(
            @PathVariable Long id) {

        speciesService.deleteSpecies(id);

        return "Species deleted successfully";
    }
}