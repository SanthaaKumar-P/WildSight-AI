package com.wildsight.backend.controller;

import com.wildsight.backend.dto.SpeciesCategoryRequest;
import com.wildsight.backend.dto.SpeciesCategoryResponse;
import com.wildsight.backend.service.SpeciesCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/species-categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
    name = "Species Category Management",
    description = "APIs for managing species categories"
)
@SecurityRequirement(name = "Bearer Authentication")
public class SpeciesCategoryController {

    private final SpeciesCategoryService service;

   @Operation(summary = "Create species category")
@PostMapping
public SpeciesCategoryResponse createCategory(
        @Valid @RequestBody SpeciesCategoryRequest request) {

    return service.createCategory(request);
}

    @Operation(summary = "Get all species categories")
@GetMapping
    public List<SpeciesCategoryResponse> getAllCategories() {

        return service.getAllCategories();
    }

   @Operation(summary = "Get species category by ID")
@GetMapping("/{id}")
    public SpeciesCategoryResponse getCategoryById(
            @PathVariable Long id) {

        return service.getCategoryById(id);
    }

    @Operation(summary = "Update species category")
@PutMapping("/{id}")
public SpeciesCategoryResponse updateCategory(
        @PathVariable Long id,
        @Valid @RequestBody SpeciesCategoryRequest request) {

    return service.updateCategory(id, request);
}

   @Operation(summary = "Delete species category")
@DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable Long id) {

        service.deleteCategory(id);

        return "Category deleted successfully";
    }
}