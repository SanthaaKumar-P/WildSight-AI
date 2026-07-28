package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.population.*;
import com.wildsight.backend.service.PopulationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopulationServiceImpl implements PopulationService {

    @Override
    public PopulationDashboardResponse getDashboard() {

        return PopulationDashboardResponse.builder()
                .totalPopulation(12450L)
                .speciesRichness(126L)
                .populationDensity(42.8)
                .growthRate(8.4)
                .monitoringSites(48L)
                .build();
    }

    @Override
    public List<PopulationTrendResponse> getPopulationTrend() {

        return List.of(

                PopulationTrendResponse.builder()
                        .month("Jan")
                        .population(101L)
                        .build(),

                PopulationTrendResponse.builder()
                        .month("Feb")
                        .population(108L)
                        .build(),

                PopulationTrendResponse.builder()
                        .month("Mar")
                        .population(115L)
                        .build(),

                PopulationTrendResponse.builder()
                        .month("Apr")
                        .population(122L)
                        .build(),

                PopulationTrendResponse.builder()
                        .month("May")
                        .population(130L)
                        .build()

        );

    }

    @Override
    public List<PopulationDistributionResponse> getPopulationDistribution() {

        return List.of(

                PopulationDistributionResponse.builder()
                        .species("Tiger")
                        .location("Western Ghats")
                        .population(96L)
                        .build(),

                PopulationDistributionResponse.builder()
                        .species("Elephant")
                        .location("Nilgiris")
                        .population(142L)
                        .build(),

                PopulationDistributionResponse.builder()
                        .species("Peacock")
                        .location("Anamalai")
                        .population(215L)
                        .build()

        );

    }

    @Override
    public List<MigrationResponse> getMigrationAnalysis() {

        return List.of(

                MigrationResponse.builder()
                        .species("Elephant")
                        .fromLocation("Nilgiris")
                        .toLocation("Western Ghats")
                        .build(),

                MigrationResponse.builder()
                        .species("Tiger")
                        .fromLocation("Mudumalai")
                        .toLocation("Sathyamangalam")
                        .build()

        );

    }

}