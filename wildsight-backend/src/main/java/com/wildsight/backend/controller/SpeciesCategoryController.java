package com.wildsight.backend.controller;

import com.wildsight.backend.dto.SpeciesCategoryRequest;
import com.wildsight.backend.dto.SpeciesCategoryResponse;
import com.wildsight.backend.service.SpeciesCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species-categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SpeciesCategoryController {

    private final SpeciesCategoryService service;

    @PostMapping
    public SpeciesCategoryResponse createCategory(
            @RequestBody SpeciesCategoryRequest request) {

        return service.createCategory(request);
    }

    @GetMapping
    public List<SpeciesCategoryResponse> getAllCategories() {

        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public SpeciesCategoryResponse getCategoryById(
            @PathVariable Long id) {

        return service.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public SpeciesCategoryResponse updateCategory(
            @PathVariable Long id,
            @RequestBody SpeciesCategoryRequest request) {

        return service.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable Long id) {

        service.deleteCategory(id);

        return "Category deleted successfully";
    }
}