package com.keydraft.reporting_software.reports.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.keydraft.reporting_software.reports.dto.BucketWiseReportDTO;
import com.keydraft.reporting_software.reports.dto.BucketExpenseTypeDTO;
import com.keydraft.reporting_software.reports.dto.BucketExpenseGroupDTO;
import com.keydraft.reporting_software.reports.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import com.keydraft.reporting_software.reports.dto.AverageSalesPriceDTO;
import com.keydraft.reporting_software.reports.dto.ProductionReportDTO;
import com.keydraft.reporting_software.reports.dto.ProductionReportWrapperDTO;
import com.keydraft.reporting_software.reports.dto.AverageCostDTO;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public BucketWiseReportDTO getBucketWiseReport(String bucketId, String month, String year) {
        
        List<Object[]> bucketWiseReport = reportRepository.getBucketWiseReport(bucketId, month, year);
        BucketWiseReportDTO bucketWiseReportDTO = new BucketWiseReportDTO();
        
        if (bucketWiseReport.isEmpty()) {
            return bucketWiseReportDTO;
        }
        
        bucketWiseReportDTO.setBucketName(bucketWiseReport.get(0)[0].toString());
        bucketWiseReportDTO.setDate(bucketWiseReport.get(0)[4].toString()+"-"+bucketWiseReport.get(0)[5].toString());
        
        // Group expenses by expense type
        Map<String, List<Object[]>> expenseTypeGroups = bucketWiseReport.stream()
                .collect(Collectors.groupingBy(row -> row[2].toString()));
        
        List<BucketExpenseTypeDTO> expenseTypes = new ArrayList<>();
        BigDecimal grandTotal = BigDecimal.ZERO;
        
        // Process each expense type
        for (Map.Entry<String, List<Object[]>> entry : expenseTypeGroups.entrySet()) {
            String expenseTypeName = entry.getKey();
            List<Object[]> expenses = entry.getValue();
            
            List<BucketExpenseGroupDTO> expenseGroups = new ArrayList<>();
            BigDecimal expenseTypeTotal = BigDecimal.ZERO;
            
            // Process expense groups within this expense type
            for (Object[] expense : expenses) {
                String expenseGroupName = expense[1].toString();
                BigDecimal amount = new BigDecimal(String.valueOf(((Number) expense[3]).doubleValue()));
                
                // Create a new expense group with "-" as the perMT value as shown in your example JSON
                BucketExpenseGroupDTO expenseGroup = new BucketExpenseGroupDTO(
                    expenseGroupName, 
                    amount.toString(),
                    null  // perMT is null since you're setting it as "-" in the JSON
                );
                
                expenseGroups.add(expenseGroup);
                expenseTypeTotal = expenseTypeTotal.add(amount);
            }
            
            BucketExpenseTypeDTO expenseType = new BucketExpenseTypeDTO(expenseTypeName, expenseGroups);
            expenseTypes.add(expenseType);
            grandTotal = grandTotal.add(expenseTypeTotal);
        }
        
        bucketWiseReportDTO.setExpenseTypes(expenseTypes);
        bucketWiseReportDTO.setGrandTotal(grandTotal.toString());
        
        return bucketWiseReportDTO;
    }

    //______________________ AVERAGE SALES PRICE REPORT ______________________
    public List<AverageSalesPriceDTO> getAverageSalesPrice(Long plantId, String month, String year) {
        return reportRepository.getAverageSalesPrice(plantId, month, year);
    }

    public ProductionReportWrapperDTO getProductionReport(Long plantId, String month, String year) {
        // Calculate previous month for opening stock
        int monthNum = Integer.parseInt(month);
        String prevMonth = String.format("%02d", monthNum - 1);
        
        List<ProductionReportDTO> items = reportRepository.getProductionReport(plantId, month, year, prevMonth, year);
        return new ProductionReportWrapperDTO(items);
    }

    //______________________ AVERAGE COST REPORT ______________________

    public List<AverageCostDTO> getAverageCost(String month, String year) {
        // First get production report to get accurate production totals
        ProductionReportWrapperDTO productionReport = getProductionReport(0L, month, year);
        
        // Create a map of quarry to production total
        Map<String, Double> productionTotals = new HashMap<>();
        for (ProductionReportDTO item : productionReport.getItems()) {
            productionTotals.merge(item.getQuarryName(), item.getProduction(), Double::sum);
        }
        
        // Get bucket totals from repository
        List<Object[]> bucketTotals = reportRepository.getQuarryBucketTotals(month, year);
        
        // Combine the data
        List<AverageCostDTO> results = new ArrayList<>();
        for (Object[] row : bucketTotals) {
            String quarryName = (String) row[0];
            BigDecimal bucketTotal = (BigDecimal) row[1];
            Double productionTotal = productionTotals.getOrDefault(quarryName, 0.0);
            
            // Log the values
            System.out.println("----------------------------------------");
            System.out.println("Quarry: " + quarryName);
            System.out.println("Bucket Total: " + bucketTotal);
            System.out.println("Production Total: " + productionTotal);
            
            results.add(new AverageCostDTO(
                quarryName, 
                bucketTotal, 
                BigDecimal.valueOf(productionTotal)
            ));
        }
        
        return results;
    }
}
