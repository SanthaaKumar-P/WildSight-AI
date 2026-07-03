package com.wildsight.backend.dto;

import com.wildsight.backend.entity.ReportStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequest {

    @NotNull
    private Long surveyId;

    @NotNull
    private Long generatedBy;

    private String reportTitle;

    private String reportType;

    private String reportPath;

    private ReportStatus reportStatus;
}