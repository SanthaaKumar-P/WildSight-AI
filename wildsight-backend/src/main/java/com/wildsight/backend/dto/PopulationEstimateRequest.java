package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PopulationEstimateRequest {

    @NotNull
    private Long speciesId;

    @NotNull
    private Long surveyId;

    private Integer estimatedPopulation;

    private BigDecimal density;

    private BigDecimal growthRate;

    private String migrationPattern;
}