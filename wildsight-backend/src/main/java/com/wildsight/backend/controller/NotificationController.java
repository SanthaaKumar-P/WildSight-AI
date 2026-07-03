package com.wildsight.backend.controller;

import com.wildsight.backend.dto.NotificationRequest;
import com.wildsight.backend.dto.NotificationResponse;
import com.wildsight.backend.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse createNotification(
            @Valid @RequestBody NotificationRequest request) {

        return notificationService.createNotification(request);
    }

    @GetMapping
    public List<NotificationResponse> getAllNotifications() {

        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationResponse getNotificationById(
            @PathVariable Long id) {

        return notificationService.getNotificationById(id);
    }

    @PutMapping("/{id}")
    public NotificationResponse updateNotification(
            @PathVariable Long id,
            @Valid @RequestBody NotificationRequest request) {

        return notificationService.updateNotification(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return "Notification deleted successfully";
    }
}