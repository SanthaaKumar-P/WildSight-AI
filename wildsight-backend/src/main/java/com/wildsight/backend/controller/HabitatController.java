package com.wildsight.backend.controller;

import com.wildsight.backend.dto.HabitatRequest;
import com.wildsight.backend.dto.HabitatResponse;
import com.wildsight.backend.service.HabitatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/habitats")
@RequiredArgsConstructor
@Tag(
    name = "Habitat Management",
    description = "APIs for managing habitats"
)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class HabitatController {

    private final HabitatService habitatService;
    
    @Operation(summary = "Create habitat")
    @PostMapping
    public HabitatResponse createHabitat(
            @Valid @RequestBody HabitatRequest request) {

        return habitatService.createHabitat(request);
    }

    @Operation(summary = "Get all habitats")
    @GetMapping
    public List<HabitatResponse> getAllHabitats() {

        return habitatService.getAllHabitats();
    }

    @Operation(summary = "Get habitat by ID")
    @GetMapping("/{id}")
    public HabitatResponse getHabitatById(
            @PathVariable Long id) {

        return habitatService.getHabitatById(id);
    }

    @Operation(summary = "Update habitat")
    @PutMapping("/{id}")
    public HabitatResponse updateHabitat(
            @PathVariable Long id,
            @Valid @RequestBody HabitatRequest request) {

        return habitatService.updateHabitat(id, request);
    }

    @Operation(summary = "Delete habitat")
    @DeleteMapping("/{id}")
    public String deleteHabitat(
            @PathVariable Long id) {

        habitatService.deleteHabitat(id);

        return "Habitat deleted successfully";
    }
}