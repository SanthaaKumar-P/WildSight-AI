package com.wildsight.backend.controller;

import com.wildsight.backend.dto.PopulationHistoryRequest;
import com.wildsight.backend.dto.PopulationHistoryResponse;
import com.wildsight.backend.service.PopulationHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/population-history")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PopulationHistoryController {

    private final PopulationHistoryService populationHistoryService;

    @PostMapping
    public PopulationHistoryResponse createHistory(
            @Valid @RequestBody PopulationHistoryRequest request) {

        return populationHistoryService.createHistory(request);
    }

    @GetMapping
    public List<PopulationHistoryResponse> getAllHistory() {

        return populationHistoryService.getAllHistory();
    }

    @GetMapping("/{id}")
    public PopulationHistoryResponse getHistoryById(
            @PathVariable Long id) {

        return populationHistoryService.getHistoryById(id);
    }

    @PutMapping("/{id}")
    public PopulationHistoryResponse updateHistory(
            @PathVariable Long id,
            @Valid @RequestBody PopulationHistoryRequest request) {

        return populationHistoryService.updateHistory(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteHistory(
            @PathVariable Long id) {

        populationHistoryService.deleteHistory(id);

        return "Population History deleted successfully";
    }
}