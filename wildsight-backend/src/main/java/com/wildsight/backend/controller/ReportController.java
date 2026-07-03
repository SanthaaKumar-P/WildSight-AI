package com.wildsight.backend.controller;

import com.wildsight.backend.dto.ReportRequest;
import com.wildsight.backend.dto.ReportResponse;
import com.wildsight.backend.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ReportResponse createReport(
            @Valid @RequestBody ReportRequest request) {

        return reportService.createReport(request);
    }

    @GetMapping
    public List<ReportResponse> getAllReports() {

        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ReportResponse getReportById(
            @PathVariable Long id) {

        return reportService.getReportById(id);
    }

    @PutMapping("/{id}")
    public ReportResponse updateReport(
            @PathVariable Long id,
            @Valid @RequestBody ReportRequest request) {

        return reportService.updateReport(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteReport(
            @PathVariable Long id) {

        reportService.deleteReport(id);

        return "Report deleted successfully";
    }
}