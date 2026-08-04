package com.wildsight.backend.repository;

import com.wildsight.backend.entity.Species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SpeciesRepository 
        extends JpaRepository<Species, Long> {


    Optional<Species> findByCommonName(String commonName);


}