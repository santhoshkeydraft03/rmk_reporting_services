package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keydraft.reporting_software.master.model.ExpenseGroup;

public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup, Long> {
    boolean existsByNameAndExpenseType_ExpenseTypeId(String name, Long expenseTypeId);
} 