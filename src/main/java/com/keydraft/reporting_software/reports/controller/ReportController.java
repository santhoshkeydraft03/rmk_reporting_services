package com.keydraft.reporting_software.reports.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keydraft.reporting_software.reports.dto.BucketWiseReportDTO;
import com.keydraft.reporting_software.reports.dto.ProductionReportWrapperDTO;
import com.keydraft.reporting_software.reports.dto.AverageCostDTO;
import com.keydraft.reporting_software.reports.dto.MaterialCostDTO;
import com.keydraft.reporting_software.reports.service.ReportService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    //______________________ BUCKET WISE REPORT ______________________
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
    public ResponseEntity<ProductionReportWrapperDTO> getProductionReport(
        @RequestParam Long plantId,
        @RequestParam String month,
        @RequestParam String year
    ) {
        return ResponseEntity.ok(reportService.getProductionReport(plantId, month, year));
    }

    //______________________ AVERAGE COST REPORT ______________________

    @GetMapping("/average-cost")
    public ResponseEntity<List<AverageCostDTO>> getAverageCost(
        @RequestParam String month,
        @RequestParam String year
    ) {
        return ResponseEntity.ok(reportService.getAverageCost(month, year));
    }

    //______________________ MATERIAL COST REPORT ______________________
    @GetMapping("/material-cost")
    public ResponseEntity<List<MaterialCostDTO>> getMaterialCost(
        @RequestParam String month,
        @RequestParam String year
    ) {
        return ResponseEntity.ok(reportService.getMaterialCost(month, year));
    }

}
