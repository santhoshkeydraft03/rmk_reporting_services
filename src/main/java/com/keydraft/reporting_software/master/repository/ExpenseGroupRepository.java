package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.keydraft.reporting_software.master.model.ExpenseGroup;
import java.util.List;

public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup, Long> {
    boolean existsByNameAndExpenseType_ExpenseTypeId(String name, Long expenseTypeId);
    
    @Query("SELECT eg FROM ExpenseGroup eg WHERE " +
           "(:expenseTypeId IS NULL OR eg.expenseType.expenseTypeId = :expenseTypeId) AND " +
           "(:status IS NULL OR eg.status = :status)")
    List<ExpenseGroup> findByFilters(@Param("expenseTypeId") Long expenseTypeId, 
                                   @Param("status") Integer status);
} 