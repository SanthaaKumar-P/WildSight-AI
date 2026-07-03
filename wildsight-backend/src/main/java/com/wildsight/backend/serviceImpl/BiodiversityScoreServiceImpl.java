package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.BiodiversityScoreRequest;
import com.wildsight.backend.dto.BiodiversityScoreResponse;
import com.wildsight.backend.entity.BiodiversityScore;
import com.wildsight.backend.entity.Habitat;
import com.wildsight.backend.entity.Survey;
import com.wildsight.backend.repository.BiodiversityScoreRepository;
import com.wildsight.backend.repository.HabitatRepository;
import com.wildsight.backend.repository.SurveyRepository;
import com.wildsight.backend.service.BiodiversityScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiodiversityScoreServiceImpl implements BiodiversityScoreService {

    private final BiodiversityScoreRepository biodiversityScoreRepository;
    private final SurveyRepository surveyRepository;
    private final HabitatRepository habitatRepository;

    @Override
    public BiodiversityScoreResponse createScore(BiodiversityScoreRequest request) {

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        Habitat habitat = null;

        if (request.getHabitatId() != null) {
            habitat = habitatRepository.findById(request.getHabitatId())
                    .orElseThrow(() -> new RuntimeException("Habitat not found"));
        }

        BiodiversityScore score = BiodiversityScore.builder()
                .survey(survey)
                .habitat(habitat)
                .speciesDiversityScore(request.getSpeciesDiversityScore())
                .habitatQualityScore(request.getHabitatQualityScore())
                .ecosystemHealthScore(request.getEcosystemHealthScore())
                .overallScore(request.getOverallScore())
                .speciesCount(request.getSpeciesCount())
                .healthStatus(request.getHealthStatus())
                .build();

        score = biodiversityScoreRepository.save(score);

        return mapToResponse(score);
    }

    @Override
    public List<BiodiversityScoreResponse> getAllScores() {

        return biodiversityScoreRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BiodiversityScoreResponse getScoreById(Long id) {

        BiodiversityScore score = biodiversityScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Biodiversity Score not found"));

        return mapToResponse(score);
    }

    @Override
    public BiodiversityScoreResponse updateScore(Long id,
                                                 BiodiversityScoreRequest request) {

        BiodiversityScore score = biodiversityScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Biodiversity Score not found"));

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        Habitat habitat = null;

        if (request.getHabitatId() != null) {
            habitat = habitatRepository.findById(request.getHabitatId())
                    .orElseThrow(() -> new RuntimeException("Habitat not found"));
        }

        score.setSurvey(survey);
        score.setHabitat(habitat);
        score.setSpeciesDiversityScore(request.getSpeciesDiversityScore());
        score.setHabitatQualityScore(request.getHabitatQualityScore());
        score.setEcosystemHealthScore(request.getEcosystemHealthScore());
        score.setOverallScore(request.getOverallScore());
        score.setSpeciesCount(request.getSpeciesCount());
        score.setHealthStatus(request.getHealthStatus());

        score = biodiversityScoreRepository.save(score);

        return mapToResponse(score);
    }

    @Override
    public void deleteScore(Long id) {

        BiodiversityScore score = biodiversityScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Biodiversity Score not found"));

        biodiversityScoreRepository.delete(score);
    }

    private BiodiversityScoreResponse mapToResponse(BiodiversityScore score) {

        return BiodiversityScoreResponse.builder()
                .biodiversityId(score.getBiodiversityId())
                .surveyId(score.getSurvey().getSurveyId())
                .surveyName(score.getSurvey().getSurveyName())
                .habitatId(score.getHabitat() != null ? score.getHabitat().getHabitatId() : null)
                .habitatName(score.getHabitat() != null ? score.getHabitat().getHabitatName() : null)
                .speciesDiversityScore(score.getSpeciesDiversityScore())
                .habitatQualityScore(score.getHabitatQualityScore())
                .ecosystemHealthScore(score.getEcosystemHealthScore())
                .overallScore(score.getOverallScore())
                .speciesCount(score.getSpeciesCount())
                .healthStatus(score.getHealthStatus())
                .calculatedAt(score.getCalculatedAt())
                .build();
    }
}