package com.keydraft.reporting_software.input.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.Plant;

import jakarta.persistence.*;

@Entity
@Table(name = "input_vsi_hours")
public class VsiHours extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "quarry_id")
    private Plant quarry;
    
    @Column(name = "vsi_hours", nullable = false)
    private Double vsiHours;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plant getQuarry() {
        return quarry;
    }

    public void setQuarry(Plant quarry) {
        this.quarry = quarry;
    }

    public Double getVsiHours() {
        return vsiHours;
    }

    public void setVsiHours(Double vsiHours) {
        this.vsiHours = vsiHours;
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