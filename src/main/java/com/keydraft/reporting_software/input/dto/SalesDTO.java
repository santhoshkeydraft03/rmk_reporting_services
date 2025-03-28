package com.keydraft.reporting_software.input.dto;

import java.math.BigDecimal;

import com.keydraft.reporting_software.common.enums.BillingStatus;
import com.keydraft.reporting_software.common.enums.PaymentType;
import com.keydraft.reporting_software.common.enums.ProductStatus;

public class SalesDTO {
    private long salesId;
    private String productName;
    private String quarryName;
    private Double salesInTons;
    private BigDecimal salesInValue;
    private ProductStatus productionStatus;
    private BillingStatus billingStatus;
    private PaymentType paymentType;
    private BigDecimal gstValue;

    public SalesDTO(long salesId, String productName, String quarryName, Double salesInTons, BigDecimal salesInValue, ProductStatus productionStatus, BillingStatus billingStatus, PaymentType paymentType, BigDecimal gstValue) {
        this.salesId = salesId;
        this.productName = productName;
        this.quarryName = quarryName;
        this.salesInTons = salesInTons;
        this.salesInValue = salesInValue;
        this.productionStatus = productionStatus;
        this.billingStatus = billingStatus;
        this.paymentType = paymentType;
        this.gstValue = gstValue;
    }

    // Getters and Setters
    public long getSalesId() {
        return salesId;
    }

    public void setSalesId(long salesId) {
        this.salesId = salesId;
    }

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

    public Double getSalesInTons() {
        return salesInTons;
    }

    public void setSalesInTons(Double salesInTons) {
        this.salesInTons = salesInTons;
    }

    public BigDecimal getSalesInValue() {
        return salesInValue;
    }

    public void setSalesInValue(BigDecimal salesInValue) {
        this.salesInValue = salesInValue;
    }

    public ProductStatus getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(ProductStatus productionStatus) {
        this.productionStatus = productionStatus;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getGstValue() {
        return gstValue;
    }

    public void setGstValue(BigDecimal gstValue) {
        this.gstValue = gstValue;
    }
}
