package com.wildsight.backend.controller;

import com.wildsight.backend.dto.EnvironmentConditionRequest;
import com.wildsight.backend.dto.EnvironmentConditionResponse;
import com.wildsight.backend.service.EnvironmentConditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/environment-conditions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EnvironmentConditionController {

    private final EnvironmentConditionService environmentConditionService;

    @PostMapping
    public EnvironmentConditionResponse createCondition(
            @Valid @RequestBody EnvironmentConditionRequest request) {

        return environmentConditionService.createCondition(request);
    }

    @GetMapping
    public List<EnvironmentConditionResponse> getAllConditions() {

        return environmentConditionService.getAllConditions();
    }

    @GetMapping("/{id}")
    public EnvironmentConditionResponse getConditionById(
            @PathVariable Long id) {

        return environmentConditionService.getConditionById(id);
    }

    @PutMapping("/{id}")
    public EnvironmentConditionResponse updateCondition(
            @PathVariable Long id,
            @Valid @RequestBody EnvironmentConditionRequest request) {

        return environmentConditionService.updateCondition(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCondition(
            @PathVariable Long id) {

        environmentConditionService.deleteCondition(id);

        return "Environment Condition deleted successfully";
    }
}