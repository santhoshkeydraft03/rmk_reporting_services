package com.keydraft.reporting_software.master.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.model.Base;

import jakarta.persistence.*;

@Entity
@Table(name = "master_expense_group", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"name", "expense_type_id"}))
public class ExpenseGroup  extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseGroupId;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_type_id", nullable = false)
    private ExpenseType expenseType;

    public long getExpenseGroupId() {
        return expenseGroupId;
    }

    public void setExpenseGroupId(long expenseGroupId) {
        this.expenseGroupId = expenseGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    
}