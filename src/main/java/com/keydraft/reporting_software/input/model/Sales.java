package com.keydraft.reporting_software.input.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.keydraft.reporting_software.common.enums.BillingStatus;
import com.keydraft.reporting_software.common.enums.PaymentType;
import com.keydraft.reporting_software.common.enums.ProductStatus;
import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.Product;
import com.keydraft.reporting_software.master.model.Plant;

import jakarta.persistence.*;

@Entity
@Table(name = "input_sales")
public class Sales extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesId;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarry_id", nullable = false)
    private Plant quarry;
    
    @Column(name = "sales_tons", nullable = false)
    private Double salesInTons;
    
    @Column(name = "sales_value", nullable = false)
    private BigDecimal salesInValue;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)       
    private ProductStatus productionStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingStatus billingStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;
    
    @Column(name = "gst_value")
    private BigDecimal gstValue;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    public long getSalesId() {
        return salesId;
    }

    public void setSalesId(long salesId) {
        this.salesId = salesId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Plant getQuarry() {
        return quarry;
    }

    public void setQuarry(Plant quarry) {
        this.quarry = quarry;
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