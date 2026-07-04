package com.wildsight.backend.controller;

import com.wildsight.backend.dto.BiodiversityScoreRequest;
import com.wildsight.backend.dto.BiodiversityScoreResponse;
import com.wildsight.backend.service.BiodiversityScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
@RestController
@RequestMapping("/api/biodiversity-scores")
@RequiredArgsConstructor
@Tag(
    name = "Biodiversity Score Management",
    description = "APIs for managing biodiversity scores"
)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class BiodiversityScoreController {

    private final BiodiversityScoreService biodiversityScoreService;

    @Operation(summary = "Create biodiversity score")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER')")
    @PostMapping
    public BiodiversityScoreResponse createScore(
            @Valid @RequestBody BiodiversityScoreRequest request) {

        return biodiversityScoreService.createScore(request);
    }

    @Operation(summary = "Get all biodiversity scores")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<BiodiversityScoreResponse> getAllScores() {

        return biodiversityScoreService.getAllScores();
    }

    @Operation(summary = "Get biodiversity score by ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public BiodiversityScoreResponse getScoreById(
            @PathVariable Long id) {

        return biodiversityScoreService.getScoreById(id);
    }

    @Operation(summary = "Update biodiversity score")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER')")
    @PutMapping("/{id}")
    public BiodiversityScoreResponse updateScore(
            @PathVariable Long id,
            @Valid @RequestBody BiodiversityScoreRequest request) {

        return biodiversityScoreService.updateScore(id, request);
    }

    @Operation(summary = "Delete biodiversity score")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteScore(
            @PathVariable Long id) {

        biodiversityScoreService.deleteScore(id);

        return "Biodiversity Score deleted successfully";
    }
}