package com.wildsight.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeciesCategoryRequest {

    private String categoryName;

    private String description;
}