package com.wildsight.backend.service;

import com.wildsight.backend.dto.BiodiversityScoreRequest;
import com.wildsight.backend.dto.BiodiversityScoreResponse;

import java.util.List;

public interface BiodiversityScoreService {

    BiodiversityScoreResponse createScore(BiodiversityScoreRequest request);

    List<BiodiversityScoreResponse> getAllScores();

    BiodiversityScoreResponse getScoreById(Long id);

    BiodiversityScoreResponse updateScore(
            Long id,
            BiodiversityScoreRequest request);

    void deleteScore(Long id);
}