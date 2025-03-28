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
@Table(name = "master_other_expenses")
public class OtherExpenses extends Base implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long otherExpenseId;
    
    @Column(name = "expenseType", nullable = false, unique = true)
    private String expenseType;

    public long getOtherExpenseId() {
        return otherExpenseId;
    }

    public void setOtherExpenseId(long otherExpenseId) {
        this.otherExpenseId = otherExpenseId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    } 

    
    
}