package com.keydraft.reporting_software.input.repository;

import com.keydraft.reporting_software.input.dto.ExpenseDTO;
import com.keydraft.reporting_software.input.model.Expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT new com.keydraft.reporting_software.input.dto.ExpenseDTO(e.id, oe.expenseType, e.amount) FROM Expense e join e.otherExpenses oe")
    List<ExpenseDTO> getAllExpense();

    boolean existsByMonthAndYear(String month, String year);
}