package com.keydraft.reporting_software.reports.dto;

import java.util.List;

public class BucketWiseReportDTO {
    private String bucketName;
    private String date;
    private String grandTotal;
    private List<BucketExpenseTypeDTO> expenseTypes;
    private double total;

    public String getBucketName() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public List<BucketExpenseTypeDTO> getExpenseTypes() {
        return expenseTypes;
    }
    public void setExpenseTypes(List<BucketExpenseTypeDTO> expenseTypes) {
        this.expenseTypes = expenseTypes;
    }
    public BucketWiseReportDTO(String bucketName, String date, List<BucketExpenseTypeDTO> expenseTypes, double total) {
        this.bucketName = bucketName;
        this.date = date;
        this.expenseTypes = expenseTypes;
        this.total = total;
    }
    public String getGrandTotal() {
        return grandTotal;
    }
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }

    public BucketWiseReportDTO() {
    }

    

    
}
