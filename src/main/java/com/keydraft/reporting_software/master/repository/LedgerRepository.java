package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.master.model.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    boolean existsByLedgerName(String ledgerName);
} 