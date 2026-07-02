package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioDetectionRequest {

    @NotNull
    private Long audioId;

    @NotNull
    private Long speciesId;

    private BigDecimal confidence;

    private String detectedCall;

    private BigDecimal noiseLevel;
}