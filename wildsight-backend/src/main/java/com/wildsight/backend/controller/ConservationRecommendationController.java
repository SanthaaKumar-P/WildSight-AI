package com.wildsight.backend.controller;

import com.wildsight.backend.dto.ConservationRecommendationRequest;
import com.wildsight.backend.dto.ConservationRecommendationResponse;
import com.wildsight.backend.service.ConservationRecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
@RestController
@RequestMapping("/api/conservation-recommendations")
@RequiredArgsConstructor
@Tag(
    name = "Conservation Recommendation Management",
    description = "APIs for managing conservation recommendations"
)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class ConservationRecommendationController {

    private final ConservationRecommendationService recommendationService;

    @Operation(summary = "Create conservation recommendation")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER')")
    @PostMapping
    public ConservationRecommendationResponse createRecommendation(
            @Valid @RequestBody ConservationRecommendationRequest request) {

        return recommendationService.createRecommendation(request);
    }

    @Operation(summary = "Get all conservation recommendations")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ConservationRecommendationResponse> getAllRecommendations() {

        return recommendationService.getAllRecommendations();
    }

    @Operation(summary = "Get conservation recommendation by ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ConservationRecommendationResponse getRecommendationById(
            @PathVariable Long id) {

        return recommendationService.getRecommendationById(id);
    }

    @Operation(summary = "Update conservation recommendation")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER')")
    @PutMapping("/{id}")
    public ConservationRecommendationResponse updateRecommendation(
            @PathVariable Long id,
            @Valid @RequestBody ConservationRecommendationRequest request) {

        return recommendationService.updateRecommendation(id, request);
    }

    @Operation(summary = "Delete conservation recommendation")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteRecommendation(
            @PathVariable Long id) {

        recommendationService.deleteRecommendation(id);

        return "Recommendation deleted successfully";
    }
}