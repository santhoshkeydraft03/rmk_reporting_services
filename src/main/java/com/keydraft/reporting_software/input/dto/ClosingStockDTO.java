package com.keydraft.reporting_software.input.dto;


public class ClosingStockDTO {
    private Long closingStockId;
    private String materialName;    // Changed to match Excel column header
    private String quarryName;
    private Double closingStockInTons;

    public ClosingStockDTO(Long closingStockId, String materialName, String quarryName, Double closingStockInTons) {
        this.closingStockId = closingStockId;
        this.materialName = materialName;
        this.quarryName = quarryName;
        this.closingStockInTons = closingStockInTons;
    }   

    // Getters and Setters
    public Long getClosingStockId() {
        return closingStockId;
    }

    public void setClosingStockId(Long closingStockId) {
        this.closingStockId = closingStockId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getQuarryName() {
        return quarryName;
    }

    public void setQuarryName(String quarryName) {
        this.quarryName = quarryName;
    }

    public Double getClosingStockInTons() {
        return closingStockInTons;
    }

    public void setClosingStockInTons(Double closingStockInTons) {
        this.closingStockInTons = closingStockInTons;
    }
} 