package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.AIPredictionResponse;
import com.wildsight.backend.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final RestTemplate restTemplate;

    private static final String AI_URL =
            "http://127.0.0.1:8000/predict-image";

    @Override
    public String predictImage(File imageFile) {

        System.out.println("====================================");
        System.out.println("Calling WildSight AI...");
        System.out.println("Image : " + imageFile.getAbsolutePath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(imageFile));

        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<AIPredictionResponse> response =
                restTemplate.postForEntity(
                        AI_URL,
                        request,
                        AIPredictionResponse.class
                );

        System.out.println("HTTP Status : " + response.getStatusCode());

        AIPredictionResponse ai = response.getBody();

        if (ai == null) {
            throw new RuntimeException("AI Service returned an empty response.");
        }

        System.out.println("Species : " + ai.getPredictedSpecies());
        System.out.println("Confidence : " + ai.getConfidence());
        System.out.println("Model : " + ai.getModel());
        System.out.println("====================================");

        return ai.getPredictedSpecies();
    }
}