package com.wildsight.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    private Long userId;

    private String title;

    private String message;

    private String notificationType;

    private Boolean isRead;

    private java.time.LocalDateTime readAt;
}