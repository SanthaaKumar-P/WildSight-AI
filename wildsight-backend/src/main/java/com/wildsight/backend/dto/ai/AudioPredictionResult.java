package com.wildsight.backend.dto.ai;

import lombok.Data;


@Data
public class AudioPredictionResult {


    private String species;


    private String speciesCode;


    private String scientificName;


    private Double confidence;


}