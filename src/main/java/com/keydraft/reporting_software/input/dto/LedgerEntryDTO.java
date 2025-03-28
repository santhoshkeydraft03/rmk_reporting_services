package com.keydraft.reporting_software.input.dto;

import java.math.BigDecimal;

public class LedgerEntryDTO {
    private Long ledgerEntryId;
    private String ledgerName;
    private BigDecimal amount;

    // Getters and Setters
    public Long getLedgerEntryId() {
        return ledgerEntryId;
    }

    public void setLedgerEntryId(Long ledgerEntryId) {
        this.ledgerEntryId = ledgerEntryId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LedgerEntryDTO(Long ledgerEntryId, String ledgerName, BigDecimal amount) {
        this.ledgerEntryId = ledgerEntryId;
        this.ledgerName = ledgerName;
        this.amount = amount;
    }
}