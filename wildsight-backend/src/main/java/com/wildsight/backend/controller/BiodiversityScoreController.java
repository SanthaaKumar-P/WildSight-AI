package com.wildsight.backend.controller;

import com.wildsight.backend.dto.BiodiversityScoreRequest;
import com.wildsight.backend.dto.BiodiversityScoreResponse;
import com.wildsight.backend.service.BiodiversityScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biodiversity-scores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BiodiversityScoreController {

    private final BiodiversityScoreService biodiversityScoreService;

    @PostMapping
    public BiodiversityScoreResponse createScore(
            @Valid @RequestBody BiodiversityScoreRequest request) {

        return biodiversityScoreService.createScore(request);
    }

    @GetMapping
    public List<BiodiversityScoreResponse> getAllScores() {

        return biodiversityScoreService.getAllScores();
    }

    @GetMapping("/{id}")
    public BiodiversityScoreResponse getScoreById(
            @PathVariable Long id) {

        return biodiversityScoreService.getScoreById(id);
    }

    @PutMapping("/{id}")
    public BiodiversityScoreResponse updateScore(
            @PathVariable Long id,
            @Valid @RequestBody BiodiversityScoreRequest request) {

        return biodiversityScoreService.updateScore(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteScore(
            @PathVariable Long id) {

        biodiversityScoreService.deleteScore(id);

        return "Biodiversity Score deleted successfully";
    }
}