package com.wildsight.backend.dto;

import com.wildsight.backend.entity.RecommendationPriority;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConservationRecommendationRequest {

    private Long biodiversityId;

    @NotNull
    private RecommendationPriority priority;

    private String recommendation;
}