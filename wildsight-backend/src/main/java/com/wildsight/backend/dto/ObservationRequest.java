package com.wildsight.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ObservationRequest {

    private Long surveyId;

    private Long speciesId;

    private Long locationId;

    private Long deviceId;

    private LocalDateTime observationTime;

    private String observerNotes;

    private String weather;

    private BigDecimal confidenceScore;

    private Integer imageCount;

    private Integer audioCount;
}
