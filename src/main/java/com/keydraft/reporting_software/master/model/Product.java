package com.keydraft.reporting_software.master.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.enums.ProductGroup;
import com.keydraft.reporting_software.common.enums.ProductionType;
import com.keydraft.reporting_software.common.model.Base;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "products", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"productName", "quarry_id"})
})
public class Product extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "quarry_id")
    private Plant quarry;
    
    @Enumerated(EnumType.STRING)
    private ProductGroup productGroup;
    
    @Enumerated(EnumType.STRING)
    private ProductionType production;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Plant getQuarry() {
        return quarry;
    }

    public void setQuarry(Plant quarry) {
        this.quarry = quarry;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public ProductionType getProduction() {
        return production;
    }

    public void setProduction(ProductionType production) {
        this.production = production;
    }
}