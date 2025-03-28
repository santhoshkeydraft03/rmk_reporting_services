package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.keydraft.reporting_software.master.model.ExpenseType;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    // Basic CRUD operations are provided by JpaRepository
    
    // Add method to find by expense type name
    boolean existsByExpenseTypeName(String expenseTypeName);
}