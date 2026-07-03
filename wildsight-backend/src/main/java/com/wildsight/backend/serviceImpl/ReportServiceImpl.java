package com.wildsight.backend.serviceImpl;

import com.wildsight.backend.dto.ReportRequest;
import com.wildsight.backend.dto.ReportResponse;
import com.wildsight.backend.entity.Report;
import com.wildsight.backend.entity.Survey;
import com.wildsight.backend.entity.User;
import com.wildsight.backend.repository.ReportRepository;
import com.wildsight.backend.repository.SurveyRepository;
import com.wildsight.backend.repository.UserRepository;
import com.wildsight.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    @Override
    public ReportResponse createReport(ReportRequest request) {

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        User user = userRepository.findById(request.getGeneratedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Report report = Report.builder()
                .survey(survey)
                .generatedBy(user)
                .reportTitle(request.getReportTitle())
                .reportType(request.getReportType())
                .reportPath(request.getReportPath())
                .reportStatus(request.getReportStatus())
                .build();

        report = reportRepository.save(report);

        return mapToResponse(report);
    }

    @Override
    public List<ReportResponse> getAllReports() {

        return reportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ReportResponse getReportById(Long id) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        return mapToResponse(report);
    }

    @Override
    public ReportResponse updateReport(Long id,
                                       ReportRequest request) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        User user = userRepository.findById(request.getGeneratedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        report.setSurvey(survey);
        report.setGeneratedBy(user);
        report.setReportTitle(request.getReportTitle());
        report.setReportType(request.getReportType());
        report.setReportPath(request.getReportPath());
        report.setReportStatus(request.getReportStatus());

        report = reportRepository.save(report);

        return mapToResponse(report);
    }

    @Override
    public void deleteReport(Long id) {

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        reportRepository.delete(report);
    }

    private ReportResponse mapToResponse(Report report) {

        return ReportResponse.builder()
                .reportId(report.getReportId())
                .surveyId(report.getSurvey().getSurveyId())
                .surveyName(report.getSurvey().getSurveyName())
                .generatedBy(report.getGeneratedBy().getUserId())
                .generatedByName(report.getGeneratedBy().getFullName())
                .reportTitle(report.getReportTitle())
                .reportType(report.getReportType())
                .reportPath(report.getReportPath())
                .generatedAt(report.getGeneratedAt())
                .reportStatus(report.getReportStatus())
                .build();
    }
}