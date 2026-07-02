package com.wildsight.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedAudioResponse {

    private Long audioId;

    private Long observationId;

    private Long uploadedBy;

    private String uploaderName;

    private String fileName;

    private String filePath;

    private LocalDateTime capturedAt;

    private LocalDateTime uploadTime;

    private Long fileSize;

    private BigDecimal durationSeconds;
}