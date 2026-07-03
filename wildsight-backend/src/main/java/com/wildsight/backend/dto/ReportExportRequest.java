package com.wildsight.backend.dto;

import com.wildsight.backend.entity.ExportFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportExportRequest {

    @NotNull
    private Long reportId;

    private ExportFormat exportFormat;

    private String exportPath;
}