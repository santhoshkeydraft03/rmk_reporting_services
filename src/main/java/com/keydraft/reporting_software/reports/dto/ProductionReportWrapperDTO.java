package com.keydraft.reporting_software.reports.dto;

import java.util.List;

public class ProductionReportWrapperDTO {
    private List<ProductionReportDTO> items;
    private Double grandTotal;

    public ProductionReportWrapperDTO(List<ProductionReportDTO> items) {
        this.items = items;
        this.grandTotal = items.stream()
                .mapToDouble(ProductionReportDTO::getProduction)
                .sum();
    }

    public List<ProductionReportDTO> getItems() {
        return items;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }
} 