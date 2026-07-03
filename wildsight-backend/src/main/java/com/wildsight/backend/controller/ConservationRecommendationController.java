package com.wildsight.backend.controller;

import com.wildsight.backend.dto.ConservationRecommendationRequest;
import com.wildsight.backend.dto.ConservationRecommendationResponse;
import com.wildsight.backend.service.ConservationRecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conservation-recommendations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConservationRecommendationController {

    private final ConservationRecommendationService recommendationService;

    @PostMapping
    public ConservationRecommendationResponse createRecommendation(
            @Valid @RequestBody ConservationRecommendationRequest request) {

        return recommendationService.createRecommendation(request);
    }

    @GetMapping
    public List<ConservationRecommendationResponse> getAllRecommendations() {

        return recommendationService.getAllRecommendations();
    }

    @GetMapping("/{id}")
    public ConservationRecommendationResponse getRecommendationById(
            @PathVariable Long id) {

        return recommendationService.getRecommendationById(id);
    }

    @PutMapping("/{id}")
    public ConservationRecommendationResponse updateRecommendation(
            @PathVariable Long id,
            @Valid @RequestBody ConservationRecommendationRequest request) {

        return recommendationService.updateRecommendation(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRecommendation(
            @PathVariable Long id) {

        recommendationService.deleteRecommendation(id);

        return "Recommendation deleted successfully";
    }
}