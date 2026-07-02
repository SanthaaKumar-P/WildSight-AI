package com.wildsight.backend.controller;

import com.wildsight.backend.dto.UploadedAudioRequest;
import com.wildsight.backend.dto.UploadedAudioResponse;
import com.wildsight.backend.service.UploadedAudioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/uploaded-audio")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UploadedAudioController {

    private final UploadedAudioService uploadedAudioService;

    @PostMapping
    public UploadedAudioResponse createAudio(
            @Valid @RequestBody UploadedAudioRequest request) {

        return uploadedAudioService.uploadAudio(request);
    }

    @GetMapping
    public List<UploadedAudioResponse> getAllAudio() {

        return uploadedAudioService.getAllAudio();
    }

    @GetMapping("/{id}")
    public UploadedAudioResponse getAudioById(
            @PathVariable Long id) {

        return uploadedAudioService.getAudioById(id);
    }

    @PutMapping("/{id}")
    public UploadedAudioResponse updateAudio(
            @PathVariable Long id,
            @Valid @RequestBody UploadedAudioRequest request) {

        return uploadedAudioService.updateAudio(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteAudio(
            @PathVariable Long id) {

        uploadedAudioService.deleteAudio(id);

        return "Uploaded Audio deleted successfully";
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public UploadedAudioResponse uploadAudio(

            @RequestParam("file") MultipartFile file,

            @RequestParam Long observationId,

            @RequestParam Long uploadedBy,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime capturedAt,

            @RequestParam BigDecimal durationSeconds

    ) throws IOException {

        return uploadedAudioService.uploadAudio(
                file,
                observationId,
                uploadedBy,
                capturedAt,
                durationSeconds
        );
    }
}