package com.wildsight.backend.dto;

import com.wildsight.backend.entity.RiskLevel;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndangeredSpeciesRequest {

    @NotNull
    private Long speciesId;

    @NotNull
    private RiskLevel riskLevel;

    private String remarks;
}