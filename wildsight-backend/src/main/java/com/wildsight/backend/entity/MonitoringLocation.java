package com.wildsight.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "monitoring_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitoringLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "survey_id", nullable = false)
    private Long surveyId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;

    private String district;

    private String state;

    private String country;
}