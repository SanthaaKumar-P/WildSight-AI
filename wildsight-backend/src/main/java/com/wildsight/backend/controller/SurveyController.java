package com.wildsight.backend.controller;

import com.wildsight.backend.dto.SurveyRequest;
import com.wildsight.backend.dto.SurveyResponse;
import com.wildsight.backend.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
    name = "Survey Management",
    description = "APIs for managing wildlife surveys"
)
@SecurityRequirement(name = "Bearer Authentication")
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "Create a new survey")
@PostMapping
public SurveyResponse createSurvey(
        @Valid @RequestBody SurveyRequest request) {

    return surveyService.createSurvey(request);
}

    @Operation(summary = "Get all surveys")
@GetMapping
    public List<SurveyResponse> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

   @Operation(summary = "Get survey by ID")
@GetMapping("/{id}")
    public SurveyResponse getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @Operation(summary = "Update survey")
@PutMapping("/{id}")
public SurveyResponse updateSurvey(
        @PathVariable Long id,
        @Valid @RequestBody SurveyRequest request) {

    return surveyService.updateSurvey(id, request);
}
    @Operation(summary = "Delete survey")
    @DeleteMapping("/{id}")
    public String deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return "Survey deleted successfully";
    }
}
