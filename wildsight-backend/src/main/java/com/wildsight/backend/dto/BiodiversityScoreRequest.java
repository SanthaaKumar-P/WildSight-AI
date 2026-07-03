package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiodiversityScoreRequest {

    @NotNull
    private Long surveyId;

    private Long habitatId;

    private BigDecimal speciesDiversityScore;

    private BigDecimal habitatQualityScore;

    private BigDecimal ecosystemHealthScore;

    private BigDecimal overallScore;

    private Integer speciesCount;

    private String healthStatus;
}