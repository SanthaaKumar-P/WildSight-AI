package com.wildsight.backend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConservationPriorityResponse {

    private String priority;

    private String reason;

    private String recommendedAction;

    private Double overallScore;

}