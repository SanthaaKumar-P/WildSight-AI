package com.wildsight.backend.repository;

import com.wildsight.backend.entity.BiodiversityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiodiversityScoreRepository
        extends JpaRepository<BiodiversityScore, Long> {

}