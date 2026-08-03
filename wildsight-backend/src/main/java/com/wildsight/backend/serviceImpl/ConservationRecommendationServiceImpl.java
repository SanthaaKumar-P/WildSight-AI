package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.ConservationRecommendationRequest;
import com.wildsight.backend.dto.ConservationRecommendationResponse;
import com.wildsight.backend.entity.BiodiversityScore;
import com.wildsight.backend.entity.ConservationRecommendation;
import com.wildsight.backend.repository.BiodiversityScoreRepository;
import com.wildsight.backend.repository.ConservationRecommendationRepository;
import com.wildsight.backend.repository.PopulationEstimateRepository;
import com.wildsight.backend.repository.PopulationHistoryRepository;
import com.wildsight.backend.service.ConservationRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.wildsight.backend.dto.ConservationPriorityResponse;
import com.wildsight.backend.entity.PopulationTrend;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConservationRecommendationServiceImpl
        implements ConservationRecommendationService {

    private final ConservationRecommendationRepository recommendationRepository;

    private final BiodiversityScoreRepository biodiversityScoreRepository;

    private final PopulationEstimateRepository populationEstimateRepository;

    private final PopulationHistoryRepository populationHistoryRepository;

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
    @Override
public ConservationPriorityResponse getConservationPriority() {

    BigDecimal biodiversityValue =
            biodiversityScoreRepository.getAverageOverallScore();

    BigDecimal growthValue =
            populationEstimateRepository.getAverageGrowthRate();

    double biodiversity =
            biodiversityValue != null
                    ? biodiversityValue.doubleValue()
                    : 0.0;

    double growth =
            growthValue != null
                    ? growthValue.doubleValue()
                    : 0.0;

    Long increasing =
            populationHistoryRepository.countByTrend(
                    PopulationTrend.INCREASING);

    Long stable =
            populationHistoryRepository.countByTrend(
                    PopulationTrend.STABLE);

    Long decreasing =
            populationHistoryRepository.countByTrend(
                    PopulationTrend.DECLINING);

    increasing = increasing == null ? 0L : increasing;
    stable = stable == null ? 0L : stable;
    decreasing = decreasing == null ? 0L : decreasing;

    String priority;
    String reason;
    String action;

    if (biodiversity < 50 || decreasing > increasing) {

        priority = "HIGH";

        reason =
                "Wildlife population is declining and biodiversity is below the safe threshold.";

        action =
                "Increase habitat restoration, strengthen anti-poaching patrols and deploy additional AI monitoring.";

    }
    else if (biodiversity < 75) {

        priority = "MEDIUM";

        reason =
                "Moderate ecosystem condition detected.";

        action =
                "Increase periodic surveys and improve habitat management.";

    }
    else {

        priority = "LOW";

        reason =
                "Healthy biodiversity with stable wildlife population.";

        action =
                "Continue routine conservation and monitoring.";

    }

    return ConservationPriorityResponse.builder()

            .biodiversityScore(
                    Math.round(biodiversity * 100.0) / 100.0)

            .averageGrowthRate(
                    Math.round(growth * 100.0) / 100.0)

            .increasingSpecies(increasing)

            .stableSpecies(stable)

            .decreasingSpecies(decreasing)

            .conservationPriority(priority)

            .reason(reason)

            .recommendedAction(action)

            .build();
}
    private ConservationRecommendationResponse mapToResponse(
            ConservationRecommendation recommendation) {

        return ConservationRecommendationResponse.builder()

                .recommendationId(
                        recommendation.getRecommendationId())

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