package com.keydraft.reporting_software.reports.dto;

public class ProductionReportDTO {
    private String productName;
    private String quarryName;
    private Double openingStock;
    private Double closingStock;
    private Double salesTonnage;
    private Double production;

    public ProductionReportDTO(String productName, String quarryName, Double openingStock, Double closingStock, Double salesTonnage) {
        this.productName = productName;
        this.quarryName = quarryName;
        this.openingStock = openingStock;
        this.closingStock = closingStock;
        this.salesTonnage = salesTonnage;
        this.production = salesTonnage + closingStock - openingStock;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuarryName() {
        return quarryName;
    }

    public void setQuarryName(String quarryName) {
        this.quarryName = quarryName;
    }

    public Double getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(Double openingStock) {
        this.openingStock = openingStock;
    }

    public Double getClosingStock() {
        return closingStock;
    }

    public void setClosingStock(Double closingStock) {
        this.closingStock = closingStock;
    }

    public Double getSalesTonnage() {
        return salesTonnage;
    }

    public void setSalesTonnage(Double salesTonnage) {
        this.salesTonnage = salesTonnage;
    }

    public Double getProduction() {
        return production;
    }

    public void setProduction(Double production) {
        this.production = production;
    }
} 