package com.keydraft.reporting_software.reports.dto;

import java.util.List;

public class BucketExpenseTypeDTO {
    private String expenseTypeName;
    private List<BucketExpenseGroupDTO> expenseGroups;

    public BucketExpenseTypeDTO(String expenseTypeName, List<BucketExpenseGroupDTO> expenseGroups) {
        this.expenseTypeName = expenseTypeName;
        this.expenseGroups = expenseGroups;
    }

    public String getExpenseTypeName() {
        return expenseTypeName;
    }

    public List<BucketExpenseGroupDTO> getExpenseGroups() {
        return expenseGroups;
    }   
    
}
