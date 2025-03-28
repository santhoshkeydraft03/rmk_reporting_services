package com.keydraft.reporting_software.input.model;

import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.OtherExpenses;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "input_expenses")
public class Expense extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseId;

    @ManyToOne
    @JoinColumn(name = "other_expenses_id")
    private OtherExpenses otherExpenses;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    // Getters and Setters
    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public OtherExpenses getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(OtherExpenses otherExpenses) {
        this.otherExpenses = otherExpenses;
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