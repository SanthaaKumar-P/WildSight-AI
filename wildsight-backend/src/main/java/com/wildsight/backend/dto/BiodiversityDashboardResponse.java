package com.wildsight.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiodiversityDashboardResponse {

    private Long totalAssessments;

    private Integer totalSpecies;

    private BigDecimal averageSpeciesDiversity;

    private BigDecimal averageHabitatQuality;

    private BigDecimal averageEcosystemHealth;

    private BigDecimal averageOverallScore;

    private Long healthyCount;

    private Long vulnerableCount;

    private Long criticalCount;
}