package com.wildsight.backend.controller;

import com.wildsight.backend.dto.SurveyRequest;
import com.wildsight.backend.dto.SurveyResponse;
import com.wildsight.backend.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public SurveyResponse createSurvey(@Valid @RequestBody SurveyRequest request) {
        return surveyService.createSurvey(request);
    }

    @GetMapping
    public List<SurveyResponse> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public SurveyResponse getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @PutMapping("/{id}")
    public SurveyResponse updateSurvey(@PathVariable Long id,
                                       @Valid @RequestBody SurveyRequest request) {
        return surveyService.updateSurvey(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return "Survey deleted successfully";
    }
}
