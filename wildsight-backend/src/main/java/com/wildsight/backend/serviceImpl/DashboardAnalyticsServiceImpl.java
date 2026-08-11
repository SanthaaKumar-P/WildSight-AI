package com.wildsight.backend.serviceImpl;


import com.wildsight.backend.dto.BiodiversityDashboardResponse;
import com.wildsight.backend.dto.HabitatDashboardResponse;
import com.wildsight.backend.dto.population.PopulationDashboardResponse;

import com.wildsight.backend.repository.*;

import com.wildsight.backend.service.DashboardAnalyticsService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class DashboardAnalyticsServiceImpl 
        implements DashboardAnalyticsService {



    private final PopulationEstimateRepository populationRepository;

    private final BiodiversityScoreRepository biodiversityRepository;

    private final HabitatRepository habitatRepository;

    private final ConservationLocationRepository locationRepository;



    // =====================================================
    // POPULATION ANALYTICS
    // =====================================================

    @Override
    public PopulationDashboardResponse getPopulationAnalytics(){


        Long totalPopulation =
                populationRepository
                .getTotalEstimatedPopulation();



        Long speciesRichness =
                populationRepository
                .getSpeciesCount();



        Double density =
                populationRepository
                .getAverageDensity()
                != null
                ?
                populationRepository
                .getAverageDensity()
                .doubleValue()
                :
                0.0;



        Double growthRate =
                populationRepository
                .getAverageGrowthRate()
                != null
                ?
                populationRepository
                .getAverageGrowthRate()
                .doubleValue()
                :
                0.0;



        Long monitoringSites =
                locationRepository.count();



        return PopulationDashboardResponse.builder()

                .totalPopulation(
                        totalPopulation != null
                        ?
                        totalPopulation
                        :
                        0L
                )

                .speciesRichness(
                        speciesRichness != null
                        ?
                        speciesRichness
                        :
                        0L
                )

                .populationDensity(
                        density
                )

                .growthRate(
                        growthRate
                )

                .monitoringSites(
                        monitoringSites
                )

                .build();

    }





    // =====================================================
    // BIODIVERSITY ANALYTICS
    // =====================================================


    @Override
    public BiodiversityDashboardResponse 
    getBiodiversityAnalytics(){



        Long totalAssessments =
                biodiversityRepository.count();



        Integer totalSpecies =
                biodiversityRepository
                .getTotalSpeciesCount();



        Double speciesDiversity =
                biodiversityRepository
                .getAverageSpeciesDiversityScore();



        Double habitatQuality =
                biodiversityRepository
                .getAverageHabitatQuality();



        Double ecosystemHealth =
                biodiversityRepository
                .getAverageEcosystemHealth()
                != null
                ?
                biodiversityRepository
                .getAverageEcosystemHealth()
                .doubleValue()
                :
                0.0;



        Double overallScore =
                biodiversityRepository
                .getAverageOverallScore()
                != null
                ?
                biodiversityRepository
                .getAverageOverallScore()
                .doubleValue()
                :
                0.0;




        Long healthy =
                biodiversityRepository
                .countByHealthStatus(
                        "Healthy"
                );



        Long vulnerable =
                biodiversityRepository
                .countByHealthStatus(
                        "Vulnerable"
                );



        Long critical =
                biodiversityRepository
                .countByHealthStatus(
                        "Critical"
                );




        return BiodiversityDashboardResponse.builder()

                .totalAssessments(
                        totalAssessments
                )

                .totalSpecies(
                        totalSpecies
                )

                .averageSpeciesDiversity(
                        speciesDiversity
                )

                .averageHabitatQuality(
                        habitatQuality
                )

                .averageEcosystemHealth(
                        ecosystemHealth
                )

                .averageOverallScore(
                        overallScore
                )

                .healthyCount(
                        healthy
                )

                .vulnerableCount(
                        vulnerable
                )

                .criticalCount(
                        critical
                )

                .build();


    }





    // =====================================================
    // HABITAT ANALYTICS
    // =====================================================


    @Override
    public HabitatDashboardResponse 
    getHabitatAnalytics(){



        Long totalHabitats =
                habitatRepository.count();



        Double quality =
                habitatRepository
                .getAverageHabitatQualityScore();



        Double suitability =
                habitatRepository
                .getAverageSuitabilityScore();



        Long healthy =
                habitatRepository
                .countHealthyHabitats();



        Long degraded =
                habitatRepository
                .countDegradedHabitats();



        Long critical =
                habitatRepository
                .countCriticalHabitats();





        return HabitatDashboardResponse.builder()


                .totalHabitats(
                        totalHabitats
                )

                .averageHabitatQuality(
                        quality
                )

                .averageSuitability(
                        suitability
                )

                .healthyHabitats(
                        healthy
                )

                .degradedHabitats(
                        degraded
                )

                .criticalHabitats(
                        critical
                )

                .build();

    }


}