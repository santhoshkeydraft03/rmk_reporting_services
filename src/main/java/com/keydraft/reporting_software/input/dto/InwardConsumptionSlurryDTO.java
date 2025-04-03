package com.keydraft.reporting_software.input.dto;

public class InwardConsumptionSlurryDTO {
    private long id;
    private String particulars;
    private String quarryName;
    private Double tonnage;
    private String month;
    private String year;

    public InwardConsumptionSlurryDTO(long id, String particulars, String quarryName, Double tonnage, String month, String year) {
        this.id = id;
        this.particulars = particulars;
        this.quarryName = quarryName;
        this.tonnage = tonnage;
        this.month = month;
        this.year = year;
    }
    

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getQuarryName() {
        return quarryName;
    }

    public void setQuarryName(String quarryName) {
        this.quarryName = quarryName;
    }

    public Double getTonnage() {
        return tonnage;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
} 