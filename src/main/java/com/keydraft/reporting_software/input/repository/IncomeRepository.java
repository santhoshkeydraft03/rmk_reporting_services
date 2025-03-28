package com.keydraft.reporting_software.input.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.input.dto.IncomeDTO;
import com.keydraft.reporting_software.input.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT new com.keydraft.reporting_software.input.dto.IncomeDTO(i.incomeId, oi.incomeType, i.amount) FROM Income i JOIN i.otherIncomes oi")
    List<IncomeDTO> getAllIncome();

    boolean existsByMonthAndYear(String month, String year);
}