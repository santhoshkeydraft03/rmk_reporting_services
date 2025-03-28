package com.keydraft.reporting_software.input.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.keydraft.reporting_software.common.model.Base;
import com.keydraft.reporting_software.master.model.Ledger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "input_ledger_entries")
public class LedgerEntry extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ledgerEntryId;
    
    @ManyToOne
    @JoinColumn(name = "ledger_id", nullable = false)
    private Ledger ledger;
    
    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    // Getters and Setters
    public long getEntryId() {
        return ledgerEntryId;
    }

    public void setEntryId(long ledgerEntryId) {
        this.ledgerEntryId = ledgerEntryId;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
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