package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.ObservationRequest;
import com.wildsight.backend.dto.ObservationResponse;
import com.wildsight.backend.entity.*;
import com.wildsight.backend.repository.*;
import com.wildsight.backend.service.ObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservationServiceImpl implements ObservationService {

    private final ObservationRepository observationRepository;
    private final SurveyRepository surveyRepository;
    private final SpeciesRepository speciesRepository;
    private final MonitoringLocationRepository monitoringLocationRepository;
    private final MonitoringDeviceRepository monitoringDeviceRepository;

    @Override
    public ObservationResponse createObservation(ObservationRequest request) {

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        Species species = speciesRepository.findById(request.getSpeciesId())
                .orElseThrow(() -> new RuntimeException("Species not found"));

        MonitoringLocation location = monitoringLocationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        MonitoringDevice device = monitoringDeviceRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        Observation observation = Observation.builder()
                .survey(survey)
                .species(species)
                .location(location)
                .device(device)
                .observationTime(request.getObservationTime())
                .observerNotes(request.getObserverNotes())
                .weather(request.getWeather())
                .confidenceScore(request.getConfidenceScore())
                .imageCount(request.getImageCount())
                .audioCount(request.getAudioCount())
                .build();

        observation = observationRepository.save(observation);

        return mapToResponse(observation);
    }

    @Override
    public List<ObservationResponse> getAllObservations() {

        return observationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ObservationResponse getObservationById(Long id) {

        Observation observation = observationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        return mapToResponse(observation);
    }

    @Override
    public ObservationResponse updateObservation(Long id,
                                                 ObservationRequest request) {

        Observation observation = observationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        Species species = speciesRepository.findById(request.getSpeciesId())
                .orElseThrow(() -> new RuntimeException("Species not found"));

        MonitoringLocation location = monitoringLocationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        MonitoringDevice device = monitoringDeviceRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        observation.setSurvey(survey);
        observation.setSpecies(species);
        observation.setLocation(location);
        observation.setDevice(device);
        observation.setObservationTime(request.getObservationTime());
        observation.setObserverNotes(request.getObserverNotes());
        observation.setWeather(request.getWeather());
        observation.setConfidenceScore(request.getConfidenceScore());
        observation.setImageCount(request.getImageCount());
        observation.setAudioCount(request.getAudioCount());

        observationRepository.save(observation);

        return mapToResponse(observation);
    }

    @Override
    public void deleteObservation(Long id) {

        Observation observation = observationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        observationRepository.delete(observation);
    }

    private ObservationResponse mapToResponse(Observation observation) {

        return ObservationResponse.builder()
                .observationId(observation.getObservationId())

                .surveyId(observation.getSurvey().getSurveyId())
                .surveyName(observation.getSurvey().getSurveyName())

                .speciesId(observation.getSpecies().getSpeciesId())
                .commonName(observation.getSpecies().getCommonName())

                .locationId(observation.getLocation().getLocationId())
                .locationName(observation.getLocation().getLocationName())

                .deviceId(observation.getDevice().getDeviceId())
                .deviceName(observation.getDevice().getDeviceName())

                .observationTime(observation.getObservationTime())
                .observerNotes(observation.getObserverNotes())
                .weather(observation.getWeather())
                .confidenceScore(observation.getConfidenceScore())
                .imageCount(observation.getImageCount())
                .audioCount(observation.getAudioCount())
                .build();
    }
}