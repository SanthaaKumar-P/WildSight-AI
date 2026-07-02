package com.wildsight.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "surveys")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long surveyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "survey_name", nullable = false, length = 150)
    private String surveyName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "habitat_type", length = 100)
    private String habitatType;

    @Column(name = "protected_area", length = 150)
    private String protectedArea;

    @Column(name = "survey_date", nullable = false)
    private LocalDate surveyDate;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
}