package com.keydraft.reporting_software.input.dto;

import java.math.BigDecimal;

public class ExpenseDTO {
    private Long expenseId;
    private String expenseName;
    private BigDecimal amount;

    public ExpenseDTO(Long expenseId, String expenseName, BigDecimal amount) {
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.amount = amount;
    }   

    // Getters and Setters
    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
} 