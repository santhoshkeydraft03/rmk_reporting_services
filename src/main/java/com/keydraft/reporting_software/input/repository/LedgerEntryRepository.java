package com.keydraft.reporting_software.input.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.input.dto.LedgerEntryDTO;
import com.keydraft.reporting_software.input.model.LedgerEntry;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {

    @Query("SELECT new com.keydraft.reporting_software.input.dto.LedgerEntryDTO(le.id, le.ledger.ledgerName, le.amount) FROM LedgerEntry le")
    List<LedgerEntryDTO> getAllLedgerEntries();
    
    boolean existsByMonthAndYear(String month, String year);
}