package com.keydraft.reporting_software.reports.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;

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
import com.keydraft.reporting_software.reports.dto.MaterialCostDTO;
import com.keydraft.reporting_software.input.dto.VsiHoursDTO;

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
        bucketWiseReportDTO
                .setDate(bucketWiseReport.get(0)[4].toString() + "-" + bucketWiseReport.get(0)[5].toString());

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

                // Create a new expense group with "-" as the perMT value as shown in your
                // example JSON
                BucketExpenseGroupDTO expenseGroup = new BucketExpenseGroupDTO(
                        expenseGroupName,
                        amount.toString(),
                        null // perMT is null since you're setting it as "-" in the JSON
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

    // ______________________ AVERAGE SALES PRICE REPORT ______________________
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

    // ______________________ AVERAGE COST REPORT ______________________

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
                    BigDecimal.valueOf(productionTotal)));
        }

        return results;
    }

    // ______________________ MATERIAL COST REPORT ______________________
    public List<MaterialCostDTO> getMaterialCost(String month, String year) {
        // 1st step
        // Get the previous month for opening stock
        int monthNum = Integer.parseInt(month);
        String prevMonth = String.format("%02d", monthNum - 1);
        if (monthNum == 1) {
            prevMonth = "12";
            year = String.valueOf(Integer.parseInt(year) - 1);
        }

        // Get average costs per quarry
        List<AverageCostDTO> avgCosts = getAverageCost(prevMonth, year);
        Map<String, BigDecimal> avgCostMap = avgCosts.stream()
                .collect(Collectors.toMap(
                        AverageCostDTO::getQuarry,
                        cost -> cost.getAvgCost()));

        // Get opening stock quantities for CHAKKAI
        List<Object[]> openingStocks = reportRepository.getChakkaiOpeningStock(prevMonth, year);
        List<MaterialCostDTO> materialCosts = new ArrayList<>();

        // Calculate opening values (quantity * average cost)
        for (Object[] row : openingStocks) {
            String quarry = (String) row[0];
            BigDecimal quantity = (BigDecimal) row[1];
            BigDecimal avgCost = avgCostMap.getOrDefault(quarry, BigDecimal.ZERO);

            MaterialCostDTO dto = new MaterialCostDTO();
            dto.setQuarry(quarry);
            dto.setQuantity(quantity);
            dto.setAvgCost(avgCost);
            dto.setOpeningValue(quantity.multiply(avgCost));
            materialCosts.add(dto);
        }

        // 2nd step
        // Get production and transport bucket totals for each quarry
        List<Object[]> bucketTotals = reportRepository.getProductionTransportBucketTotals(month, year);
        Map<String, BigDecimal> bucketTotalMap = new HashMap<>();
        for (Object[] row : bucketTotals) {
            bucketTotalMap.put((String) row[0], (BigDecimal) row[1]);
        }

        // Add bucket totals to the DTOs
        for (MaterialCostDTO dto : materialCosts) {
            dto.setBucketValue(bucketTotalMap.getOrDefault(dto.getQuarry(), BigDecimal.ZERO));
        }

        // 3rd step - Calculate crusher average cost
        // Get crusher bucket's grand total
        BigDecimal crusherBucketTotal = reportRepository.getCrusherBucketTotal("CRUSHER", month, year);

        // Get VSI hours for all quarries
        List<VsiHoursDTO> vsiHoursList = reportRepository.getVsiHoursByMonthAndYear(month, year);

        // Calculate total VSI hours
        BigDecimal totalVsiHours = vsiHoursList.stream()
                .map(dto -> BigDecimal.valueOf(dto.getVsiHours()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create a map of quarry to VSI hours
        Map<String, BigDecimal> vsiHoursMap = vsiHoursList.stream()
                .collect(Collectors.toMap(
                        VsiHoursDTO::getQuarryName,
                        dto -> BigDecimal.valueOf(dto.getVsiHours())));

        // Calculate and add crusher average cost to each DTO
        for (MaterialCostDTO dto : materialCosts) {
            BigDecimal quarryVsiHours = vsiHoursMap.getOrDefault(dto.getQuarry(), BigDecimal.ZERO);
            BigDecimal crusherAvgCost = totalVsiHours.compareTo(BigDecimal.ZERO) > 0
                    ? crusherBucketTotal.multiply(quarryVsiHours).divide(totalVsiHours, 2, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO;
            dto.setCrusherAvgCost(crusherAvgCost);
        }

        // 4th step - Add closing values for current month
        // Get closing stock quantities for current month
        List<Object[]> closingStocks = reportRepository.getChakkaiOpeningStock(month, year);
        Map<String, BigDecimal> closingQuantityMap = new HashMap<>();
        for (Object[] row : closingStocks) {
            closingQuantityMap.put((String) row[0], (BigDecimal) row[1]);
        }

        // Add closing values to DTOs
        for (MaterialCostDTO dto : materialCosts) {
            dto.setClosingValue(closingQuantityMap.getOrDefault(dto.getQuarry(), BigDecimal.ZERO));
        }

        return materialCosts;
    }
}
