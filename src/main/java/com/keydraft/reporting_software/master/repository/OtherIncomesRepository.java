package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.master.model.OtherIncomes;

@Repository
public interface OtherIncomesRepository extends JpaRepository<OtherIncomes, Long> {
    boolean existsByIncomeType(String incomeType);
}