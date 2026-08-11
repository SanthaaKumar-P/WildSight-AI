package com.wildsight.backend.serviceImpl;


import com.wildsight.backend.dto.MapLocationResponse;
import com.wildsight.backend.entity.ConservationLocation;
import com.wildsight.backend.repository.ConservationLocationRepository;
import com.wildsight.backend.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl 
implements DashboardService {



private final ConservationLocationRepository locationRepository;



@Override
public List<MapLocationResponse> getMapLocations(){


return locationRepository.findAll()
.stream()
.map(location ->

MapLocationResponse.builder()

.id(location.getLocationId())

.name(location.getLocationName())

.latitude(location.getLatitude())

.longitude(location.getLongitude())

.type(location.getLocationType())

.state(location.getState())

.healthScore(0.0)

.speciesCount(0L)

.population(0L)

.build()

)

.toList();


}



}