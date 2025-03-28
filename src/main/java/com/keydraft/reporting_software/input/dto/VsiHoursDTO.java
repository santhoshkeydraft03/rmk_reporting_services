package com.keydraft.reporting_software.input.dto;

public class VsiHoursDTO {
    private long vsiHoursId;
    private String quarryName;
    private Double vsiHours;

    

    public VsiHoursDTO(long vsiHoursId, String quarryName, Double vsiHours) {
        this.vsiHoursId = vsiHoursId;
        this.quarryName = quarryName;
        this.vsiHours = vsiHours;
    }

    // Getters and Setters
    public long getVsiHoursId() {
        return vsiHoursId;
    }

    public void setVsiHoursId(long vsiHoursId) {
        this.vsiHoursId = vsiHoursId;
    }

    public String getQuarryName() {
        return quarryName;
    }

    public void setQuarryName(String quarryName) {
        this.quarryName = quarryName;
    }

    public Double getVsiHours() {
        return vsiHours;
    }

    public void setVsiHours(Double vsiHours) {
        this.vsiHours = vsiHours;
    }
} 