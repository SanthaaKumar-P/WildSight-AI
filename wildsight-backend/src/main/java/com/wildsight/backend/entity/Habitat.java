package com.wildsight.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habitats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitat_id")
    private Long habitatId;

    @Column(name = "habitat_name", nullable = false)
    private String habitatName;

    @Column(name = "habitat_type")
    private String habitatType;

    @Column(name = "vegetation_type")
    private String vegetationType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}