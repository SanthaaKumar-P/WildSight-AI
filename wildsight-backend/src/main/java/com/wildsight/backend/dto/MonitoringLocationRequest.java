package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitoringLocationRequest {

    @NotNull
    private Long surveyId;

    @NotBlank
    private String locationName;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    private String district;

    private String state;

    private String country;
}