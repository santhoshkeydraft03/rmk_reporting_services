package com.keydraft.reporting_software.reports.dto;

import java.math.BigDecimal;

public class AverageSalesPriceDTO {
    private String plantName;
    private String productName;
    private BigDecimal averagePrice;

    public AverageSalesPriceDTO(String plantName, String productName, BigDecimal averagePrice) {
        this.plantName = plantName;
        this.productName = productName;
        this.averagePrice = averagePrice;
    }

    public AverageSalesPriceDTO() {
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

}