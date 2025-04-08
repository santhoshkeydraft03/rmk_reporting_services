package com.keydraft.reporting_software.reports.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialCostDTO {
    private String quarry;
    private BigDecimal materialCost;
    private BigDecimal quantity;
    private BigDecimal avgCost;
    private BigDecimal openingValue;
    private BigDecimal bucketValue;
    private BigDecimal crusherAvgCost;
    private BigDecimal closingValue;

    public MaterialCostDTO(String quarry, BigDecimal materialCost) {
        this.quarry = quarry;
        this.materialCost = materialCost;
    }

    public String getQuarry() {
        return quarry;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setQuarry(String quarry) {
        this.quarry = quarry;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }   
} 