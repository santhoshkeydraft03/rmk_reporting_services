package com.keydraft.reporting_software.input.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.Plant;
import com.keydraft.reporting_software.master.model.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "input_closing_stock")
public class ClosingStock extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stockId;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "quarry_id")
    private Plant quarry;
    
    @Column(name = "closing_stock_tons", nullable = false)
    private Double closingStockInTons;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    // Getters and Setters
    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
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

    public Double getClosingStockInTons() {
        return closingStockInTons;
    }

    public void setClosingStockInTons(Double closingStockInTons) {
        this.closingStockInTons = closingStockInTons;
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