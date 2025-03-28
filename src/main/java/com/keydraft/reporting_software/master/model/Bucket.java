package com.keydraft.reporting_software.master.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.enums.Category;
import com.keydraft.reporting_software.common.model.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "master_buckets")
public class Bucket extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bucketId;
    
    @Column(name = "bucket_name", nullable = false, unique = true)
    private String bucketName;
    
    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", nullable = false)
    private Category category;
    
    // Getters and Setters
    public long getBucketId() {
        return bucketId;
    }

    public void setBucketId(long bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}