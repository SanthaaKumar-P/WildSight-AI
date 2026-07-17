package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.UploadedImageRequest;
import com.wildsight.backend.dto.UploadedImageResponse;
import com.wildsight.backend.entity.Observation;
import com.wildsight.backend.entity.UploadedImage;
import com.wildsight.backend.entity.User;
import com.wildsight.backend.repository.ObservationRepository;
import com.wildsight.backend.repository.UploadedImageRepository;
import com.wildsight.backend.repository.UserRepository;
import com.wildsight.backend.service.UploadedImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.wildsight.backend.service.AIService;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.nio.file.*;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadedImageServiceImpl implements UploadedImageService {

    private final UploadedImageRepository uploadedImageRepository;
    private final ObservationRepository observationRepository;
    private final UserRepository userRepository;
    private final AIService aiService;

    @Override
    public UploadedImageResponse uploadImage(UploadedImageRequest request) {

        Observation observation = observationRepository.findById(request.getObservationId())
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        User user = userRepository.findById(request.getUploadedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UploadedImage image = UploadedImage.builder()
                .observation(observation)
                .fileName(request.getFileName())
                .filePath(request.getFilePath())
                .capturedAt(request.getCapturedAt())
                .fileSize(request.getFileSize())
                .uploadedBy(user)
                .imageQuality(request.getImageQuality())
                .build();

        image = uploadedImageRepository.save(image);

        return mapToResponse(image);
    }

    @Override
    public List<UploadedImageResponse> getAllImages() {

        return uploadedImageRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UploadedImageResponse getImageById(Long id) {

        UploadedImage image = uploadedImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        return mapToResponse(image);
    }

    @Override
    public UploadedImageResponse updateImage(Long id,
                                             UploadedImageRequest request) {

        UploadedImage image = uploadedImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        Observation observation = observationRepository.findById(request.getObservationId())
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        User user = userRepository.findById(request.getUploadedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        image.setObservation(observation);
        image.setFileName(request.getFileName());
        image.setFilePath(request.getFilePath());
        image.setCapturedAt(request.getCapturedAt());
        image.setFileSize(request.getFileSize());
        image.setUploadedBy(user);
        image.setImageQuality(request.getImageQuality());

        image = uploadedImageRepository.save(image);

        return mapToResponse(image);
    }

    @Override
    public void deleteImage(Long id) {

        UploadedImage image = uploadedImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        uploadedImageRepository.delete(image);
    }

    private UploadedImageResponse mapToResponse(UploadedImage image) {

        return UploadedImageResponse.builder()
                .imageId(image.getImageId())

                .observationId(image.getObservation().getObservationId())

                .uploadedBy(image.getUploadedBy().getUserId())
                .uploaderName(image.getUploadedBy().getFullName())

                .fileName(image.getFileName())
                .filePath(image.getFilePath())

                .capturedAt(image.getCapturedAt())
                .uploadTime(image.getUploadTime())

                .fileSize(image.getFileSize())
                .imageQuality(image.getImageQuality())

                .build();
    }
    @Override
public UploadedImageResponse uploadImage(
        MultipartFile file,
        Long observationId,
        Long uploadedBy,
        LocalDateTime capturedAt,
        BigDecimal imageQuality) throws IOException {

    Observation observation = observationRepository.findById(observationId)
            .orElseThrow(() -> new RuntimeException("Observation not found"));

    User user = userRepository.findById(uploadedBy)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Path uploadPath = Paths.get("uploads", "images");

if (Files.exists(uploadPath) && !Files.isDirectory(uploadPath)) {
    throw new RuntimeException(
            "'uploads/images' exists but is not a directory."
    );
}

Files.createDirectories(uploadPath);

    String fileName =
            UUID.randomUUID() + "_" + file.getOriginalFilename();

    Path path = uploadPath.resolve(fileName);

    Files.copy(file.getInputStream(),
            path,
            StandardCopyOption.REPLACE_EXISTING);

    UploadedImage image = UploadedImage.builder()
            .observation(observation)
            .uploadedBy(user)
            .fileName(file.getOriginalFilename())
            .filePath(path.toString())
            .fileSize(file.getSize())
            .capturedAt(capturedAt)
            .imageQuality(imageQuality)
            .build();

    image = uploadedImageRepository.save(image);

    image = uploadedImageRepository.save(image);

// Call AI Service
String species = aiService.predictImage(path.toFile());

System.out.println("====================================");
System.out.println("AI Prediction : " + species);
System.out.println("====================================");

return mapToResponse(image);
}
}