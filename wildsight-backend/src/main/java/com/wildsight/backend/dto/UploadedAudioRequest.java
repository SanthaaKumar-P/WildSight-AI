package com.wildsight.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedAudioRequest {

    @NotNull
    private Long observationId;

    private String fileName;

    private String filePath;

    private LocalDateTime capturedAt;

    private Long fileSize;

    @NotNull
    private Long uploadedBy;

    private BigDecimal durationSeconds;
}