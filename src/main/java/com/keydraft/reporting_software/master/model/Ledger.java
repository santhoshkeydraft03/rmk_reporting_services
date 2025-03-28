package com.keydraft.reporting_software.master.model;

import java.io.Serializable;

import com.keydraft.reporting_software.common.model.Base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_ledger")
public class Ledger extends Base implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ledgerId;
    
    @Column(name = "ledgerName", nullable = false, unique = true)
    private String ledgerName;
    
    @ManyToOne
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;
    
    @ManyToOne
    @JoinColumn(name = "expense_type_id")
    private ExpenseType expenseType;
    
    @ManyToOne
    @JoinColumn(name = "expense_group_id")
    private ExpenseGroup expenseGroup;

    // Getters and setters
    public long getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(long ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public ExpenseGroup getExpenseGroup() {
        return expenseGroup;
    }

    public void setExpenseGroup(ExpenseGroup expenseGroup) {
        this.expenseGroup = expenseGroup;
    }
}