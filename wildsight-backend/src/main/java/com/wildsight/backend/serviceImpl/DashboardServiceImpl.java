package com.wildsight.backend.serviceImpl;


import com.wildsight.backend.dto.LocationIntelligenceResponse;
import com.wildsight.backend.dto.MapLocationResponse;

import com.wildsight.backend.entity.ConservationLocation;

import com.wildsight.backend.repository.ConservationLocationRepository;

import com.wildsight.backend.service.DashboardService;
import com.wildsight.backend.service.LocationIntelligenceService;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;



@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {



    private final ConservationLocationRepository locationRepository;


    private final LocationIntelligenceService locationService;




    @Override
    public List<MapLocationResponse> getMapLocations(){



        return locationRepository.findAll()

                .stream()

                .map(location -> {


                    LocationIntelligenceResponse intelligence;


                    try {

                        intelligence =
                                locationService
                                .getLocationDetails(
                                        location.getLocationId()
                                );


                    }

                    catch(Exception e){


                        intelligence = null;

                    }



                    return MapLocationResponse.builder()


                            .id(
                                location.getLocationId()
                            )


                            .name(
                                location.getLocationName()
                            )


                            .latitude(
                                location.getLatitude()
                            )


                            .longitude(
                                location.getLongitude()
                            )


                            .type(
                                location.getLocationType()
                            )


                            .state(
                                location.getState()
                            )



                            .healthScore(

                                intelligence != null
                                ?
                                intelligence.getHealthScore()
                                :
                                0.0

                            )



                            .speciesCount(

                                intelligence != null
                                ?
                                intelligence.getSpeciesCount()
                                :
                                0L

                            )



                            .population(

                                intelligence != null
                                ?
                                intelligence.getPopulation()
                                :
                                0L

                            )



                            .biodiversityScore(

                                intelligence != null
                                ?
                                intelligence.getBiodiversityScore()
                                :
                                0.0

                            )



                            .habitatScore(

                                intelligence != null
                                ?
                                intelligence.getHabitatScore()
                                :
                                0.0

                            )



                            .conservationStatus(

                                intelligence != null
                                ?
                                intelligence.getConservationStatus()
                                :
                                "Unknown"

                            )



                            .threatLevel(

                                intelligence != null
                                ?
                                intelligence.getThreatLevel()
                                :
                                "Unknown"

                            )



                            .recommendation(

                                intelligence != null
                                ?
                                intelligence.getRecommendation()
                                :
                                "No recommendation available"

                            )



                            .build();



                })

                .toList();


    }



}