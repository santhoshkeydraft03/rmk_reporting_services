package com.keydraft.reporting_software.reports.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AverageCostDTO {
    private String quarry;
    private BigDecimal bucketTotal;
    private BigDecimal productionTotal;
    private BigDecimal avgCost;

    public AverageCostDTO(String quarry, BigDecimal bucketTotal, BigDecimal productionTotal) {
        this.quarry = quarry;
        this.bucketTotal = bucketTotal;
        this.productionTotal = productionTotal;
        this.avgCost = productionTotal.compareTo(BigDecimal.ZERO) > 0 
            ? bucketTotal.divide(productionTotal, 2, RoundingMode.HALF_UP)
            : BigDecimal.ZERO;
    }

    public String getQuarry() {
        return quarry;
    }

    public BigDecimal getBucketTotal() {
        return bucketTotal;
    }

    public BigDecimal getProductionTotal() {
        return productionTotal;
    }

    public BigDecimal getAvgCost() {
        return avgCost;
    }
} 