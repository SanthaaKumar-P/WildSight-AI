package com.wildsight.backend.dto.ai;

import lombok.Data;

import java.util.List;

@Data
public class Detection {

    private String species;

    private Double confidence;

    private List<Integer> boundingBox;

}