package com.wildsight.backend.repository;

import com.wildsight.backend.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SurveyRepository 
        extends JpaRepository<Survey, Long> {


    Long countByStatus(String status);


    Long countByHabitatType(String habitatType);


}