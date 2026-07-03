package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitatRequest {

    @NotBlank
    private String habitatName;

    private String habitatType;

    private String vegetationType;

    private String description;
}