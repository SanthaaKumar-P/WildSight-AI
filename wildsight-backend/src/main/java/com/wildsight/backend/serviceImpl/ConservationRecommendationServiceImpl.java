package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.ConservationRecommendationRequest;
import com.wildsight.backend.dto.ConservationRecommendationResponse;
import com.wildsight.backend.entity.BiodiversityScore;
import com.wildsight.backend.entity.ConservationRecommendation;
import com.wildsight.backend.repository.BiodiversityScoreRepository;
import com.wildsight.backend.repository.ConservationRecommendationRepository;
import com.wildsight.backend.service.ConservationRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConservationRecommendationServiceImpl
        implements ConservationRecommendationService {

    private final ConservationRecommendationRepository recommendationRepository;
    private final BiodiversityScoreRepository biodiversityScoreRepository;

    @Override
    public ConservationRecommendationResponse createRecommendation(
            ConservationRecommendationRequest request) {

        BiodiversityScore biodiversityScore = null;

        if (request.getBiodiversityId() != null) {

            biodiversityScore = biodiversityScoreRepository
                    .findById(request.getBiodiversityId())
                    .orElseThrow(() ->
                            new RuntimeException("Biodiversity Score not found"));
        }

        ConservationRecommendation recommendation =
                ConservationRecommendation.builder()
                        .biodiversityScore(biodiversityScore)
                        .priority(request.getPriority())
                        .recommendation(request.getRecommendation())
                        .build();

        recommendation = recommendationRepository.save(recommendation);

        return mapToResponse(recommendation);
    }

    @Override
    public List<ConservationRecommendationResponse> getAllRecommendations() {

        return recommendationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ConservationRecommendationResponse getRecommendationById(Long id) {

        ConservationRecommendation recommendation =
                recommendationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Recommendation not found"));

        return mapToResponse(recommendation);
    }

    @Override
    public ConservationRecommendationResponse updateRecommendation(
            Long id,
            ConservationRecommendationRequest request) {

        ConservationRecommendation recommendation =
                recommendationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Recommendation not found"));

        BiodiversityScore biodiversityScore = null;

        if (request.getBiodiversityId() != null) {

            biodiversityScore = biodiversityScoreRepository
                    .findById(request.getBiodiversityId())
                    .orElseThrow(() ->
                            new RuntimeException("Biodiversity Score not found"));
        }

        recommendation.setBiodiversityScore(biodiversityScore);
        recommendation.setPriority(request.getPriority());
        recommendation.setRecommendation(request.getRecommendation());

        recommendation = recommendationRepository.save(recommendation);

        return mapToResponse(recommendation);
    }

    @Override
    public void deleteRecommendation(Long id) {

        ConservationRecommendation recommendation =
                recommendationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Recommendation not found"));

        recommendationRepository.delete(recommendation);
    }

    private ConservationRecommendationResponse mapToResponse(
            ConservationRecommendation recommendation) {

        return ConservationRecommendationResponse.builder()
                .recommendationId(recommendation.getRecommendationId())

                .biodiversityId(
                        recommendation.getBiodiversityScore() != null
                                ? recommendation.getBiodiversityScore().getBiodiversityId()
                                : null)

                .overallScore(
                        recommendation.getBiodiversityScore() != null
                                ? recommendation.getBiodiversityScore().getOverallScore().doubleValue()
                                : null)

                .priority(recommendation.getPriority())

                .recommendation(recommendation.getRecommendation())

                .generatedAt(recommendation.getGeneratedAt())

                .build();
    }
}