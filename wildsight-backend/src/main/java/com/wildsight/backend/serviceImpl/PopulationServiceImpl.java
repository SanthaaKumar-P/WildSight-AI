package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.population.*;
import com.wildsight.backend.entity.PopulationEstimate;
import com.wildsight.backend.entity.PopulationTrend;

import com.wildsight.backend.repository.PopulationEstimateRepository;
import com.wildsight.backend.repository.PopulationHistoryRepository;

import com.wildsight.backend.service.PopulationService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PopulationServiceImpl implements PopulationService {


    private final PopulationEstimateRepository populationEstimateRepository;

    private final PopulationHistoryRepository populationHistoryRepository;



    @Override
    public PopulationDashboardResponse getDashboard() {


        Long totalPopulation =
                populationEstimateRepository
                        .getTotalEstimatedPopulation();


        Long speciesRichness =
                populationEstimateRepository
                        .getSpeciesCount();


        Double density =
                populationEstimateRepository
                        .getAverageDensity() != null

                ? populationEstimateRepository
                        .getAverageDensity()
                        .doubleValue()

                : 0.0;



        Double growthRate =
                populationEstimateRepository
                        .getAverageGrowthRate() != null

                ? populationEstimateRepository
                        .getAverageGrowthRate()
                        .doubleValue()

                : 0.0;



        return PopulationDashboardResponse.builder()

                .totalPopulation(
                        totalPopulation == null ? 0 : totalPopulation
                )

                .speciesRichness(
                        speciesRichness == null ? 0 : speciesRichness
                )

                .populationDensity(
                        density
                )

                .growthRate(
                        growthRate
                )

                .monitoringSites(0L)

                .build();

    }





    @Override
    public List<PopulationTrendResponse> getPopulationTrend() {


        Long increasing =
                populationHistoryRepository
                        .countByTrend(
                                PopulationTrend.INCREASING
                        );


        Long stable =
                populationHistoryRepository
                        .countByTrend(
                                PopulationTrend.STABLE
                        );


        Long declining =
                populationHistoryRepository
                        .countByTrend(
                                PopulationTrend.DECLINING
                        );



        return List.of(

                PopulationTrendResponse.builder()
                        .month("Increasing")
                        .population(
                                increasing == null ? 0 : increasing
                        )
                        .build(),


                PopulationTrendResponse.builder()
                        .month("Stable")
                        .population(
                                stable == null ? 0 : stable
                        )
                        .build(),


                PopulationTrendResponse.builder()
                        .month("Declining")
                        .population(
                                declining == null ? 0 : declining
                        )
                        .build()

        );

    }





    @Override
    public List<PopulationDistributionResponse>
    getPopulationDistribution() {


        return populationEstimateRepository
                .getSpeciesDistribution()

                .stream()

                .map(this::mapDistribution)

                .toList();

    }





    @Override
    public List<MigrationResponse>
    getMigrationAnalysis() {


        return populationEstimateRepository
                .getMigrationPatterns()

                .stream()

                .map(this::mapMigration)

                .toList();

    }







    private PopulationDistributionResponse mapDistribution(
            PopulationEstimate estimate) {


        return PopulationDistributionResponse.builder()

                .species(
                        estimate.getSpecies() != null
                        ? estimate.getSpecies()
                                .getCommonName()
                        : null
                )

                .location(
                        estimate.getSurvey() != null
                        ? estimate.getSurvey()
                                .getProtectedArea()
                        : null
                )

                .population(
                        estimate.getEstimatedPopulation()
                                .longValue()
                )

                .build();

    }







    private MigrationResponse mapMigration(
            PopulationEstimate estimate) {


        return MigrationResponse.builder()

                .species(
                        estimate.getSpecies() != null
                        ? estimate.getSpecies()
                                .getCommonName()
                        : null
                )

                .fromLocation(
                        estimate.getSurvey() != null
                        ? estimate.getSurvey()
                                .getProtectedArea()
                        : null
                )

                .toLocation(
                        estimate.getMigrationPattern()
                )

                .build();

    }


}