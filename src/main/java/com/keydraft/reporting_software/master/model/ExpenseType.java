package com.keydraft.reporting_software.master.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.keydraft.reporting_software.common.model.Base;

@Entity
@Table(name = "master_expense_types")
public class ExpenseType extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseTypeId;

    @Column(name = "expense_type_name", nullable = false)
    private String expenseTypeName;

    public long getExpenseTypeId() {
        return expenseTypeId;
    }

    public void setExpenseTypeId(long expenseTypeId) {
        this.expenseTypeId = expenseTypeId;
    }

    public String getExpenseTypeName() {
        return expenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        this.expenseTypeName = expenseTypeName;
    }
}