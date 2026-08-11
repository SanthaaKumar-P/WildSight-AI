package com.wildsight.backend.config;


import com.wildsight.backend.entity.ConservationLocation;
import com.wildsight.backend.repository.ConservationLocationRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.ArrayList;



@Component
@RequiredArgsConstructor
public class ConservationLocationSeeder 
        implements CommandLineRunner {


    private final ConservationLocationRepository repository;



    @Override
    public void run(String... args) {


        System.out.println(
            "========== Conservation Location Seeder =========="
        );


        if(repository.count() > 0){

            System.out.println(
                "Locations already exist"
            );

            return;
        }



        ConservationLocation bandipur =
                ConservationLocation.builder()

                .locationName(
                    "Bandipur Tiger Reserve"
                )

                .state(
                    "Karnataka"
                )

                .country(
                    "India"
                )

                .latitude(
                    11.6668
                )

                .longitude(
                    76.6250
                )

                .locationType(
                    "Tiger Reserve"
                )

                .protectedArea(
                    "YES"
                )

                .description(
                    "Important tiger and elephant habitat"
                )

                .surveys(
                    new ArrayList<>()
                )

                .build();




        ConservationLocation kaziranga =
                ConservationLocation.builder()

                .locationName(
                    "Kaziranga National Park"
                )

                .state(
                    "Assam"
                )

                .country(
                    "India"
                )

                .latitude(
                    26.5775
                )

                .longitude(
                    93.1711
                )

                .locationType(
                    "National Park"
                )

                .protectedArea(
                    "YES"
                )

                .description(
                    "One horned rhinoceros habitat"
                )

                .surveys(
                    new ArrayList<>()
                )

                .build();




        ConservationLocation gir =
                ConservationLocation.builder()

                .locationName(
                    "Gir National Park"
                )

                .state(
                    "Gujarat"
                )

                .country(
                    "India"
                )

                .latitude(
                    21.1240
                )

                .longitude(
                    70.8242
                )

                .locationType(
                    "Lion Reserve"
                )

                .protectedArea(
                    "YES"
                )

                .description(
                    "Asian lion conservation area"
                )

                .surveys(
                    new ArrayList<>()
                )

                .build();



        repository.save(bandipur);
        repository.save(kaziranga);
        repository.save(gir);



        System.out.println(
            "========== Conservation Locations Seeded =========="
        );

    }

}