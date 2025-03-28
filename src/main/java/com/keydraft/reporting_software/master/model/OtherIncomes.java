package com.keydraft.reporting_software.master.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.model.Base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_other_incomes")
public class OtherIncomes extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long otherIncomesId;
    
    @Column(name = "incomeType", nullable = false, unique = true)
    private String incomeType;

    public long getOtherIncomesId() {
        return otherIncomesId;
    }

    public void setOtherIncomesId(long otherIncomesId) {
        this.otherIncomesId = otherIncomesId;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
}