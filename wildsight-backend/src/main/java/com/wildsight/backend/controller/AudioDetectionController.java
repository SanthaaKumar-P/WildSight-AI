package com.wildsight.backend.controller;

import com.wildsight.backend.dto.AudioDetectionRequest;
import com.wildsight.backend.dto.AudioDetectionResponse;
import com.wildsight.backend.service.AudioDetectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audio-detections")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AudioDetectionController {

    private final AudioDetectionService audioDetectionService;

    @PostMapping
    public AudioDetectionResponse createDetection(
            @Valid @RequestBody AudioDetectionRequest request) {

        return audioDetectionService.createDetection(request);
    }

    @GetMapping
    public List<AudioDetectionResponse> getAllDetections() {

        return audioDetectionService.getAllDetections();
    }

    @GetMapping("/{id}")
    public AudioDetectionResponse getDetectionById(
            @PathVariable Long id) {

        return audioDetectionService.getDetectionById(id);
    }

    @PutMapping("/{id}")
    public AudioDetectionResponse updateDetection(
            @PathVariable Long id,
            @Valid @RequestBody AudioDetectionRequest request) {

        return audioDetectionService.updateDetection(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteDetection(
            @PathVariable Long id) {

        audioDetectionService.deleteDetection(id);

        return "Audio Detection deleted successfully";
    }
}