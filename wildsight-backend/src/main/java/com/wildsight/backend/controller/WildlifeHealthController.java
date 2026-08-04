package com.wildsight.backend.controller;

import com.wildsight.backend.dto.WildlifeHealthResponse;
import com.wildsight.backend.service.WildlifeHealthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api/wildlife-health")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Wildlife Health Scoring",
        description = "APIs for ecosystem health scoring"
)
@SecurityRequirement(name = "Bearer Authentication")
public class WildlifeHealthController {


    private final WildlifeHealthService wildlifeHealthService;


    @Operation(
            summary = "Calculate wildlife ecosystem health score"
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/score")
    public WildlifeHealthResponse getHealthScore(){

        return wildlifeHealthService.calculateHealthScore();

    }

}