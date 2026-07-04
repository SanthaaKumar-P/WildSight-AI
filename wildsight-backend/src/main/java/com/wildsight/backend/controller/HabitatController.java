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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER')")
    @PostMapping
    public HabitatResponse createHabitat(
            @Valid @RequestBody HabitatRequest request) {

        return habitatService.createHabitat(request);
    }

    @Operation(summary = "Get all habitats")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<HabitatResponse> getAllHabitats() {

        return habitatService.getAllHabitats();
    }

    @Operation(summary = "Get habitat by ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public HabitatResponse getHabitatById(
            @PathVariable Long id) {

        return habitatService.getHabitatById(id);
    }

    @Operation(summary = "Update habitat")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER')")
    @PutMapping("/{id}")
    public HabitatResponse updateHabitat(
            @PathVariable Long id,
            @Valid @RequestBody HabitatRequest request) {

        return habitatService.updateHabitat(id, request);
    }

    @Operation(summary = "Delete habitat")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteHabitat(
            @PathVariable Long id) {

        habitatService.deleteHabitat(id);

        return "Habitat deleted successfully";
    }
}