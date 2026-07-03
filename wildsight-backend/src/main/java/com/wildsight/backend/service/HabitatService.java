package com.wildsight.backend.service;

import com.wildsight.backend.dto.HabitatRequest;
import com.wildsight.backend.dto.HabitatResponse;

import java.util.List;

public interface HabitatService {

    HabitatResponse createHabitat(HabitatRequest request);

    List<HabitatResponse> getAllHabitats();

    HabitatResponse getHabitatById(Long id);

    HabitatResponse updateHabitat(Long id,
                                  HabitatRequest request);

    void deleteHabitat(Long id);
}