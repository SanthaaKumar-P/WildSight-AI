package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvironmentConditionRequest {

    @NotNull
    private Long habitatId;

    @NotNull
    private Long surveyId;

    private BigDecimal temperature;

    private BigDecimal humidity;

    private BigDecimal rainfall;

    private String airQuality;

    private BigDecimal windSpeed;

    private String weatherCondition;

    private LocalDateTime recordedAt;
}