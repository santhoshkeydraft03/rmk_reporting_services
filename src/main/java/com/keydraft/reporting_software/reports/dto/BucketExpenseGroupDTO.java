package com.keydraft.reporting_software.reports.dto;

import java.math.BigDecimal;

public class BucketExpenseGroupDTO {
    private String expenseGroupName;
    private String total;
    private BigDecimal perMT;

    // This constructor must match the order and types of parameters in the JPQL query
    public BucketExpenseGroupDTO(String expenseGroupName, String total, BigDecimal perMT) {
        this.expenseGroupName = expenseGroupName;
        this.total = total;
        this.perMT = perMT;
    }

    public String getExpenseGroupName() {
        return expenseGroupName;
    }

    public void setExpenseGroupName(String expenseGroupName) {
        this.expenseGroupName = expenseGroupName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public BigDecimal getPerMT() {
        return perMT;
    }

    public void setPerMT(BigDecimal perMT) {
        this.perMT = perMT;
    }

    


} 