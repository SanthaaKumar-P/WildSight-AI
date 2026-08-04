package com.wildsight.backend.config;


import com.wildsight.backend.entity.*;
import com.wildsight.backend.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {


    private final UserRepository userRepository;

    private final SpeciesRepository speciesRepository;

    private final SurveyRepository surveyRepository;

    private final HabitatRepository habitatRepository;

    private final PopulationEstimateRepository populationEstimateRepository;

    private final BiodiversityScoreRepository biodiversityScoreRepository;



    @Override
    public void run(String... args) {


        System.out.println("========== WildSight Seeder Started ==========");


        System.out.println(
                "Species Count : "
                + speciesRepository.count()
        );

        System.out.println(
                "Habitat Count : "
                + habitatRepository.count()
        );

        System.out.println(
                "Population Count : "
                + populationEstimateRepository.count()
        );

        System.out.println(
                "Biodiversity Count : "
                + biodiversityScoreRepository.count()
        );



        User user =
                userRepository
                        .findByEmail("admin@gmail.com")
                        .orElse(null);



        if(user == null){

            System.out.println(
                    "Admin user not found. Seeder stopped"
            );

            return;
        }



        /*
         * Insert Habitat Data
         */

        Habitat westernGhats =
                habitatRepository.findAll()
                        .stream()
                        .filter(
                                h -> h.getHabitatName()
                                        .equals("Western Ghats")
                        )
                        .findFirst()
                        .orElseGet(() ->

                                habitatRepository.save(

                                Habitat.builder()

                                .habitatName("Western Ghats")

                                .habitatType("Forest")

                                .vegetationType(
                                        "Tropical Evergreen"
                                )

                                .description(
                                        "Biodiversity hotspot region"
                                )

                                .habitatQualityScore(92.0)

                                .degradationLevel(15.0)

                                .vegetationDensity(88.0)

                                .temperature(24.0)

                                .humidity(80.0)

                                .rainfall(250.0)

                                .waterQuality(90.0)

                                .airQuality(95.0)

                                .suitabilityScore(94.0)

                                .build()
                        ));




        Habitat nilgiris =
                habitatRepository.findAll()
                        .stream()
                        .filter(
                                h -> h.getHabitatName()
                                        .equals("Nilgiris")
                        )
                        .findFirst()
                        .orElseGet(() ->


                                habitatRepository.save(

                                Habitat.builder()

                                .habitatName("Nilgiris")

                                .habitatType(
                                        "Mountain Forest"
                                )

                                .vegetationType(
                                        "Grassland"
                                )

                                .description(
                                        "Elephant migration corridor"
                                )

                                .habitatQualityScore(85.0)

                                .degradationLevel(25.0)

                                .vegetationDensity(80.0)

                                .temperature(20.0)

                                .humidity(75.0)

                                .rainfall(180.0)

                                .waterQuality(85.0)

                                .airQuality(90.0)

                                .suitabilityScore(88.0)

                                .build()
                        ));




        /*
 * Insert Species
 */


Species tiger =
        speciesRepository.findByCommonName(
                "Bengal Tiger"
        )
        .orElseGet(() ->

        speciesRepository.save(

        Species.builder()

        .categoryId(1L)

        .commonName(
                "Bengal Tiger"
        )

        .scientificName(
                "Panthera tigris"
        )

        .conservationStatus(
                "Endangered"
        )

        .iucnStatus(
                "EN"
        )

        .description(
                "Large carnivore species"
        )

        .build()
));





Species elephant =
        speciesRepository.findByCommonName(
                "Asian Elephant"
        )
        .orElseGet(() ->

        speciesRepository.save(

        Species.builder()

        .categoryId(1L)

        .commonName(
                "Asian Elephant"
        )

        .scientificName(
                "Elephas maximus"
        )

        .conservationStatus(
                "Endangered"
        )

        .iucnStatus(
                "EN"
        )

        .description(
                "Large herbivore species"
        )

        .build()
));





Species leopard =
        speciesRepository.findByCommonName(
                "Indian Leopard"
        )
        .orElseGet(() ->

        speciesRepository.save(

        Species.builder()

        .categoryId(1L)

        .commonName(
                "Indian Leopard"
        )

        .scientificName(
                "Panthera pardus"
        )

        .conservationStatus(
                "Vulnerable"
        )

        .iucnStatus(
                "VU"
        )

        .description(
                "Forest predator species"
        )

        .build()
));




        /*
         * Insert Surveys
         */


        Survey tigerSurvey =
                surveyRepository.save(

                Survey.builder()

                .user(user)

                .surveyName(
                        "Western Ghats Tiger Survey"
                )

                .description(
                        "Camera trap based survey"
                )

                .habitatType(
                        "Forest"
                )

                .protectedArea(
                        "Western Ghats"
                )

                .surveyDate(
                        LocalDate.now()
                )

                .status(
                        "Completed"
                )

                .build()
        );



        Survey elephantSurvey =
                surveyRepository.save(

                Survey.builder()

                .user(user)

                .surveyName(
                        "Nilgiri Elephant Survey"
                )

                .description(
                        "Migration monitoring survey"
                )

                .habitatType(
                        "Mountain Forest"
                )

                .protectedArea(
                        "Nilgiris"
                )

                .surveyDate(
                        LocalDate.now()
                )

                .status(
                        "Completed"
                )

                .build()
        );





        /*
         * Population Estimates
         */

        if(populationEstimateRepository.count()==0){


            populationEstimateRepository.save(

            PopulationEstimate.builder()

            .species(tiger)

            .survey(tigerSurvey)

            .estimatedPopulation(96)

            .density(
                    new BigDecimal("8.5")
            )

            .growthRate(
                    new BigDecimal("4.5")
            )

            .migrationPattern(
                    "Western Ghats to Nilgiris"
            )

            .build()
            );



            populationEstimateRepository.save(

            PopulationEstimate.builder()

            .species(elephant)

            .survey(elephantSurvey)

            .estimatedPopulation(142)

            .density(
                    new BigDecimal("12.5")
            )

            .growthRate(
                    new BigDecimal("6.2")
            )

            .migrationPattern(
                    "Nilgiris to Mudumalai"
            )

            .build()
            );



            populationEstimateRepository.save(

            PopulationEstimate.builder()

            .species(leopard)

            .survey(tigerSurvey)

            .estimatedPopulation(45)

            .density(
                    new BigDecimal("5.2")
            )

            .growthRate(
                    new BigDecimal("2.8")
            )

            .migrationPattern(
                    "Forest Corridor Movement"
            )

            .build()
            );

        }





        /*
         * Biodiversity Scores
         */

        if(biodiversityScoreRepository.count()==0){


            biodiversityScoreRepository.save(

            BiodiversityScore.builder()

            .survey(tigerSurvey)

            .habitat(westernGhats)

            .speciesDiversityScore(
                    new BigDecimal("92")
            )

            .habitatQualityScore(
                    new BigDecimal("90")
            )

            .ecosystemHealthScore(
                    new BigDecimal("91")
            )

            .overallScore(
                    new BigDecimal("91")
            )

            .speciesCount(35)

            .healthStatus(
                    "Healthy"
            )

            .build()
            );



            biodiversityScoreRepository.save(

            BiodiversityScore.builder()

            .survey(elephantSurvey)

            .habitat(nilgiris)

            .speciesDiversityScore(
                    new BigDecimal("85")
            )

            .habitatQualityScore(
                    new BigDecimal("86")
            )

            .ecosystemHealthScore(
                    new BigDecimal("88")
            )

            .overallScore(
                    new BigDecimal("87")
            )

            .speciesCount(28)

            .healthStatus(
                    "Healthy"
            )

            .build()
            );

        }



        System.out.println(
                "========== WildSight Seeder Completed =========="
        );


    }

}