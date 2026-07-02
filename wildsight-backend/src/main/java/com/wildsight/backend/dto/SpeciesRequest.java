package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpeciesRequest {

    private Long categoryId;

    @NotBlank
    private String commonName;

    private String scientificName;

    private String conservationStatus;

    private String iucnStatus;

    private String description;
}