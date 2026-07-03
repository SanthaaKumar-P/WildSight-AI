package com.wildsight.backend.controller;

import com.wildsight.backend.dto.ReportExportRequest;
import com.wildsight.backend.dto.ReportExportResponse;
import com.wildsight.backend.service.ReportExportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report-exports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReportExportController {

    private final ReportExportService reportExportService;

    @PostMapping
    public ReportExportResponse createExport(
            @Valid @RequestBody ReportExportRequest request) {

        return reportExportService.createExport(request);
    }

    @GetMapping
    public List<ReportExportResponse> getAllExports() {

        return reportExportService.getAllExports();
    }

    @GetMapping("/{id}")
    public ReportExportResponse getExportById(
            @PathVariable Long id) {

        return reportExportService.getExportById(id);
    }

    @PutMapping("/{id}")
    public ReportExportResponse updateExport(
            @PathVariable Long id,
            @Valid @RequestBody ReportExportRequest request) {

        return reportExportService.updateExport(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteExport(
            @PathVariable Long id) {

        reportExportService.deleteExport(id);

        return "Report Export deleted successfully";
    }
}