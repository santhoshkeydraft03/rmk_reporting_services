package com.keydraft.reporting_software.input.dto;

import java.math.BigDecimal;

public class IncomeDTO {
    private Long incomeId;
    private String incomeName;
    private BigDecimal amount;

    public IncomeDTO(Long incomeId, String incomeName, BigDecimal amount) {
        this.incomeId = incomeId;
        this.incomeName = incomeName;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}