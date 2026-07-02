package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SurveyRequest {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotBlank(message = "Survey name is required")
    private String surveyName;

    private String description;

    private String habitatType;

    private String protectedArea;

    @NotNull(message = "Survey date is required")
    private LocalDate surveyDate;

    @NotBlank(message = "Status is required")
    private String status;
}