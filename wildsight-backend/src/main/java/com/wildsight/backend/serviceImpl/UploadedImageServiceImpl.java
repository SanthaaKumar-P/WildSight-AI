package com.wildsight.backend.serviceImpl;


import com.wildsight.backend.dto.UploadedImageRequest;
import com.wildsight.backend.dto.UploadedImageResponse;
import com.wildsight.backend.dto.ai.AnimalDetection;

import com.wildsight.backend.entity.DetectionResult;
import com.wildsight.backend.entity.Observation;
import com.wildsight.backend.entity.UploadedImage;
import com.wildsight.backend.entity.User;

import com.wildsight.backend.repository.DetectionResultRepository;
import com.wildsight.backend.repository.ObservationRepository;
import com.wildsight.backend.repository.UploadedImageRepository;
import com.wildsight.backend.repository.UserRepository;

import com.wildsight.backend.service.AIService;
import com.wildsight.backend.service.UploadedImageService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class UploadedImageServiceImpl 
        implements UploadedImageService {



    private final UploadedImageRepository uploadedImageRepository;

    private final ObservationRepository observationRepository;

    private final UserRepository userRepository;

    private final AIService aiService;

    private final DetectionResultRepository detectionResultRepository;




    // ================= CRUD =================


    @Override
    public UploadedImageResponse uploadImage(
            UploadedImageRequest request) {


        Observation observation =
                observationRepository.findById(
                        request.getObservationId()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "Observation not found"
                        )
                );


        User user =
                userRepository.findById(
                        request.getUploadedBy()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );



        UploadedImage image =
                UploadedImage.builder()

                .observation(observation)

                .fileName(
                        request.getFileName()
                )

                .filePath(
                        request.getFilePath()
                )

                .capturedAt(
                        request.getCapturedAt()
                )

                .fileSize(
                        request.getFileSize()
                )

                .uploadedBy(user)

                .imageQuality(
                        request.getImageQuality()
                )

                .build();



        image =
        uploadedImageRepository.save(image);



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


        UploadedImage image =
                uploadedImageRepository.findById(id)

                .orElseThrow(
                        () -> new RuntimeException(
                                "Image not found"
                        )
                );


        return mapToResponse(image);

    }






    @Override
    public void deleteImage(Long id) {


        UploadedImage image =
                uploadedImageRepository.findById(id)

                .orElseThrow(
                        () -> new RuntimeException(
                                "Image not found"
                        )
                );


        uploadedImageRepository.delete(image);

    }







    // ================= REAL UPLOAD + AI =================


    @Override
    public UploadedImageResponse uploadImage(

            MultipartFile file,

            Long observationId,

            Long uploadedBy,

            LocalDateTime capturedAt,

            BigDecimal imageQuality

    ) throws IOException {



        Observation observation =
                observationRepository.findById(observationId)

                .orElseThrow(
                        () -> new RuntimeException(
                                "Observation not found"
                        )
                );



        User user =
                userRepository.findById(uploadedBy)

                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );




        Path uploadPath =
                Paths.get(
                        "uploads",
                        "images"
                );


        Files.createDirectories(
                uploadPath
        );



        String fileName =
                UUID.randomUUID()
                + "_"
                + file.getOriginalFilename();



        Path path =
                uploadPath.resolve(fileName);



        Files.copy(

                file.getInputStream(),

                path,

                StandardCopyOption.REPLACE_EXISTING

        );




        UploadedImage image =

                UploadedImage.builder()

                .observation(observation)

                .uploadedBy(user)

                .fileName(
                        file.getOriginalFilename()
                )

                .filePath(
                        path.toString()
                )

                .fileSize(
                        file.getSize()
                )

                .capturedAt(
                        capturedAt
                )

                .imageQuality(
                        imageQuality
                )

                .build();



        image =
        uploadedImageRepository.save(image);




        // ================= AI CALL =================


        String species =
                aiService.predictImage(
                        path.toFile()
                );


        AnimalDetection detection =
                aiService.detectAnimals(
                        path.toFile()
                );





        // ================= SAVE DETECTION RESULT =================


        if(detection.getDetections()!=null)
        {

            detection.getDetections()
            
            .forEach(animal -> {


                DetectionResult result =

                        DetectionResult.builder()

                        .observation(
                                observation
                        )

                        .sourceType(
                                "IMAGE"
                        )

                        .species(
                                animal.getSpecies()
                        )

                        .scientificName(
                                animal.getScientificName()
                        )

                        .category(
                                animal.getCategory()
                        )

                        .confidence(
                                animal.getConfidence()
                        )

                        .conservationStatus(
                                animal.getSpeciesStatus()
                        )

                        .kingdom(
                                animal.getKingdom()
                        )

                        .phylum(
                                animal.getPhylum()
                        )

                        .className(
                                animal.getClassName()
                        )

                        .order(
                                animal.getOrder()
                        )

                        .family(
                                animal.getFamily()
                        )

                        .genus(
                                animal.getGenus()
                        )

                        .build();



                detectionResultRepository.save(result);


            });

        }





        return UploadedImageResponse.builder()


                .imageId(
                        image.getImageId()
                )

                .observationId(
                        observation.getObservationId()
                )

                .uploadedBy(
                        user.getUserId()
                )

                .uploaderName(
                        user.getFullName()
                )

                .fileName(
                        image.getFileName()
                )

                .filePath(
                        image.getFilePath()
                )

                .capturedAt(
                        image.getCapturedAt()
                )

                .fileSize(
                        image.getFileSize()
                )

                .imageQuality(
                        image.getImageQuality()
                )


                .predictedSpecies(
                        species
                )

                .animalCount(
                        detection.getAnimalCount()
                )

                .detections(
                        detection.getDetections()
                )

                .annotatedImage(
                        detection.getAnnotatedImage()
                )

                .model(
                        "YOLO + MobileNetV2"
                )

                .build();

    }







    private UploadedImageResponse mapToResponse(
            UploadedImage image) {


        return UploadedImageResponse.builder()


                .imageId(
                        image.getImageId()
                )


                .observationId(
                        image.getObservation()
                        .getObservationId()
                )


                .uploadedBy(
                        image.getUploadedBy()
                        .getUserId()
                )


                .uploaderName(
                        image.getUploadedBy()
                        .getFullName()
                )


                .fileName(
                        image.getFileName()
                )


                .filePath(
                        image.getFilePath()
                )


                .capturedAt(
                        image.getCapturedAt()
                )


                .fileSize(
                        image.getFileSize()
                )


                .imageQuality(
                        image.getImageQuality()
                )

                .build();

    }





    @Override
    public UploadedImageResponse updateImage(Long id, UploadedImageRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateImage'");
    }

}