package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.keydraft.reporting_software.master.model.ExpenseType;
import java.util.List;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    // Basic CRUD operations are provided by JpaRepository
    
    // Add method to find by expense type name
    boolean existsByExpenseTypeName(String expenseTypeName);
    
    @Query("SELECT et FROM ExpenseType et WHERE :status IS NULL OR et.status = :status")
    List<ExpenseType> findByStatus(@Param("status") Integer status);
}