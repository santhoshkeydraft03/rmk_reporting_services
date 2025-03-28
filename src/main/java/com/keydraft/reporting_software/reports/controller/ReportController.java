package com.keydraft.reporting_software.reports.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keydraft.reporting_software.reports.dto.BucketWiseReportDTO;
import com.keydraft.reporting_software.reports.service.ReportService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/bucket-wise-report")
    public ResponseEntity<BucketWiseReportDTO> getBucketWiseReport(@RequestParam String bucketId, @RequestParam String month, @RequestParam String year) {
        return ResponseEntity.ok(reportService.getBucketWiseReport(bucketId, month, year));
    }

}
