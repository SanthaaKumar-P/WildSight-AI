package com.wildsight.backend.service;

import com.wildsight.backend.dto.ConservationPriorityResponse;
import com.wildsight.backend.dto.ConservationRecommendationRequest;
import com.wildsight.backend.dto.ConservationRecommendationResponse;
import com.wildsight.backend.dto.ConservationPriorityResponse;
import java.util.List;

public interface ConservationRecommendationService {

    ConservationRecommendationResponse createRecommendation(
            ConservationRecommendationRequest request);

    List<ConservationRecommendationResponse> getAllRecommendations();

    ConservationRecommendationResponse getRecommendationById(Long id);

    ConservationRecommendationResponse updateRecommendation(
            Long id,
            ConservationRecommendationRequest request);

    void deleteRecommendation(Long id);

    ConservationPriorityResponse getConservationPriority();
}