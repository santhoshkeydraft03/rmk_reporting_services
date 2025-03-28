package com.keydraft.reporting_software.input.model;


import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.OtherIncomes;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "input_incomes")
public class Income extends Base {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long incomeId;
    
    @ManyToOne
    @JoinColumn(name = "other_incomes_id")
    private OtherIncomes otherIncomes;
    
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    // Getters and Setters
    public long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(long incomeId) {
        this.incomeId = incomeId;
    }

    public OtherIncomes getOtherIncomes() {
        return otherIncomes;
    }

    public void setOtherIncomes(OtherIncomes otherIncomes) {
        this.otherIncomes = otherIncomes;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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