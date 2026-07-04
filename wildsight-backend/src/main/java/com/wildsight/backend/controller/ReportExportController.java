package com.wildsight.backend.controller;

import com.wildsight.backend.dto.ReportExportRequest;
import com.wildsight.backend.dto.ReportExportResponse;
import com.wildsight.backend.service.ReportExportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
@RestController
@RequestMapping("/api/report-exports")
@RequiredArgsConstructor
@Tag(
    name = "Report Export Management",
    description = "APIs for managing report exports"
)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class ReportExportController {

    private final ReportExportService reportExportService;
    
    @Operation(summary = "Create report export")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER','FOREST_OFFICER')")
    @PostMapping
    public ReportExportResponse createExport(
            @Valid @RequestBody ReportExportRequest request) {

        return reportExportService.createExport(request);
    }

    @Operation(summary = "Get all report exports")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER','FOREST_OFFICER')")
    @GetMapping
    public List<ReportExportResponse> getAllExports() {

        return reportExportService.getAllExports();
    }

    @Operation(summary = "Get report export by ID")
    @PreAuthorize("hasAnyRole('ADMIN','RESEARCHER','FOREST_OFFICER')")
    @GetMapping("/{id}")
    public ReportExportResponse getExportById(
            @PathVariable Long id) {

        return reportExportService.getExportById(id);
    }

    @Operation(summary = "Update report export")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ReportExportResponse updateExport(
            @PathVariable Long id,
            @Valid @RequestBody ReportExportRequest request) {

        return reportExportService.updateExport(id, request);
    }

    @Operation(summary = "Delete report export")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteExport(
            @PathVariable Long id) {

        reportExportService.deleteExport(id);

        return "Report Export deleted successfully";
    }
}