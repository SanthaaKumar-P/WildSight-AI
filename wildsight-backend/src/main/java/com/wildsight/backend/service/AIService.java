package com.wildsight.backend.service;

import java.io.File;

import com.wildsight.backend.dto.ai.AnimalDetection;

public interface AIService {

    String predictImage(File imageFile);
    AnimalDetection detectAnimals(File imageFile);
}