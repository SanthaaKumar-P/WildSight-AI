package com.wildsight.backend.controller;

import com.wildsight.backend.dto.HabitatRequest;
import com.wildsight.backend.dto.HabitatResponse;
import com.wildsight.backend.service.HabitatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HabitatController {

    private final HabitatService habitatService;

    @PostMapping
    public HabitatResponse createHabitat(
            @Valid @RequestBody HabitatRequest request) {

        return habitatService.createHabitat(request);
    }

    @GetMapping
    public List<HabitatResponse> getAllHabitats() {

        return habitatService.getAllHabitats();
    }

    @GetMapping("/{id}")
    public HabitatResponse getHabitatById(
            @PathVariable Long id) {

        return habitatService.getHabitatById(id);
    }

    @PutMapping("/{id}")
    public HabitatResponse updateHabitat(
            @PathVariable Long id,
            @Valid @RequestBody HabitatRequest request) {

        return habitatService.updateHabitat(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteHabitat(
            @PathVariable Long id) {

        habitatService.deleteHabitat(id);

        return "Habitat deleted successfully";
    }
}