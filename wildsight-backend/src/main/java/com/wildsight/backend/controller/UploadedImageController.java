package com.wildsight.backend.controller;

import com.wildsight.backend.dto.UploadedImageRequest;
import com.wildsight.backend.dto.UploadedImageResponse;
import com.wildsight.backend.service.UploadedImageService;
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
@RequestMapping("/api/uploaded-images")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UploadedImageController {

    private final UploadedImageService uploadedImageService;

    // ---------------- CRUD ----------------

    @PostMapping
    public UploadedImageResponse createImage(
            @Valid @RequestBody UploadedImageRequest request) {

        return uploadedImageService.uploadImage(request);
    }

    @GetMapping
    public List<UploadedImageResponse> getAllImages() {
        return uploadedImageService.getAllImages();
    }

    @GetMapping("/{id}")
    public UploadedImageResponse getImageById(@PathVariable Long id) {
        return uploadedImageService.getImageById(id);
    }

    @PutMapping("/{id}")
    public UploadedImageResponse updateImage(
            @PathVariable Long id,
            @Valid @RequestBody UploadedImageRequest request) {

        return uploadedImageService.updateImage(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteImage(@PathVariable Long id) {

        uploadedImageService.deleteImage(id);

        return "Uploaded Image deleted successfully";
    }

    // ---------------- REAL IMAGE UPLOAD ----------------

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public UploadedImageResponse uploadImage(

            @RequestParam("file") MultipartFile file,

            @RequestParam Long observationId,

            @RequestParam Long uploadedBy,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime capturedAt,

            @RequestParam BigDecimal imageQuality

    ) throws IOException {

        System.out.println("UPLOAD API HIT");
        System.out.println(file.getOriginalFilename());

        return uploadedImageService.uploadImage(
                file,
                observationId,
                uploadedBy,
                capturedAt,
                imageQuality
        );
    }

    // ---------------- TEST API ----------------

    @PostMapping(
            value = "/test-upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String testUpload(
            @RequestParam("file") MultipartFile file) {

        System.out.println("TEST API HIT");
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        return "SUCCESS";
    }
}