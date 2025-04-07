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

    //______________________ AVERAGE SALES PRICE REPORT ______________________
    @GetMapping("/average-sales-price")
    public ResponseEntity<?> getAverageSalesPrice(
        @RequestParam Long plantId,
        @RequestParam String month,
        @RequestParam String year
    ) {
        System.out.println("plantId: " + plantId);
        System.out.println("month: " + month);
        System.out.println("year: " + year);
        return ResponseEntity.ok(reportService.getAverageSalesPrice(plantId, month, year));
    }

    //______________________ PRODUCTION REPORT ______________________

    @GetMapping("/production-report")
    public ResponseEntity<?> getProductionReport(
        @RequestParam Long plantId,
        @RequestParam String month,
        @RequestParam String year
    ) {
        return ResponseEntity.ok(reportService.getProductionReport(plantId, month, year));
    }

}
