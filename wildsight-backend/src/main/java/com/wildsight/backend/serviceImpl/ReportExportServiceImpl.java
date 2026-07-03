package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.ReportExportRequest;
import com.wildsight.backend.dto.ReportExportResponse;
import com.wildsight.backend.entity.Report;
import com.wildsight.backend.entity.ReportExport;
import com.wildsight.backend.repository.ReportExportRepository;
import com.wildsight.backend.repository.ReportRepository;
import com.wildsight.backend.service.ReportExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportExportServiceImpl implements ReportExportService {

    private final ReportExportRepository reportExportRepository;
    private final ReportRepository reportRepository;

    @Override
    public ReportExportResponse createExport(ReportExportRequest request) {

        Report report = reportRepository.findById(request.getReportId())
                .orElseThrow(() -> new RuntimeException("Report not found"));

        ReportExport export = ReportExport.builder()
                .report(report)
                .exportFormat(request.getExportFormat())
                .exportPath(request.getExportPath())
                .build();

        export = reportExportRepository.save(export);

        return mapToResponse(export);
    }

    @Override
    public List<ReportExportResponse> getAllExports() {

        return reportExportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ReportExportResponse getExportById(Long id) {

        ReportExport export = reportExportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Export not found"));

        return mapToResponse(export);
    }

    @Override
    public ReportExportResponse updateExport(Long id,
                                             ReportExportRequest request) {

        ReportExport export = reportExportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Export not found"));

        Report report = reportRepository.findById(request.getReportId())
                .orElseThrow(() -> new RuntimeException("Report not found"));

        export.setReport(report);
        export.setExportFormat(request.getExportFormat());
        export.setExportPath(request.getExportPath());

        export = reportExportRepository.save(export);

        return mapToResponse(export);
    }

    @Override
    public void deleteExport(Long id) {

        ReportExport export = reportExportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report Export not found"));

        reportExportRepository.delete(export);
    }

    private ReportExportResponse mapToResponse(ReportExport export) {

        return ReportExportResponse.builder()
                .exportId(export.getExportId())
                .reportId(export.getReport().getReportId())
                .reportTitle(export.getReport().getReportTitle())
                .exportFormat(export.getExportFormat())
                .exportedAt(export.getExportedAt())
                .exportPath(export.getExportPath())
                .build();
    }
}