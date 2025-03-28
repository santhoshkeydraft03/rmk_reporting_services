package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.master.model.OtherExpenses;

@Repository
public interface OtherExpensesRepository extends JpaRepository<OtherExpenses, Long> {
    boolean existsByExpenseType(String expenseType);
}