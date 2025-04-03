package com.keydraft.reporting_software.input.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.Plant;

import jakarta.persistence.*;

@Entity
@Table(name = "input_inward_consumption_slurry")
public class InwardConsumptionSlurry extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "particulars", nullable = false)
    private String particulars;

    @ManyToOne
    @JoinColumn(name = "quarry_id")
    private Plant quarry;

    @Column(name = "tonnage", nullable = false)
    private Double tonnage;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

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

    public Plant getQuarry() {
        return quarry;
    }

    public void setQuarry(Plant quarry) {
        this.quarry = quarry;
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