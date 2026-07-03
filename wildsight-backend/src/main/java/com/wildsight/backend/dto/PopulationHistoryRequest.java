package com.wildsight.backend.dto;

import com.wildsight.backend.entity.PopulationTrend;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PopulationHistoryRequest {

    @NotNull
    private Long estimateId;

    private Integer previousPopulation;

    private Integer currentPopulation;

    private PopulationTrend trend;
}