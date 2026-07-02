package com.wildsight.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedImageRequest {

    private Long observationId;

    private String fileName;

    private String filePath;

    private LocalDateTime capturedAt;

    private Long fileSize;

    private Long uploadedBy;

    private BigDecimal imageQuality;
}