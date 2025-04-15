package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.keydraft.reporting_software.master.model.Ledger;
import java.util.List;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    boolean existsByLedgerName(String ledgerName);

    @Query("SELECT l FROM Ledger l " +
           "LEFT JOIN FETCH l.bucket " +
           "LEFT JOIN FETCH l.expenseType " +
           "LEFT JOIN FETCH l.expenseGroup " +
           "WHERE (:bucketId IS NULL OR l.bucket.bucketId = :bucketId) " +
           "AND (:expenseTypeId IS NULL OR l.expenseType.expenseTypeId = :expenseTypeId) " +
           "AND (:status IS NULL OR l.status = :status)")
    List<Ledger> findByFilters(
        @Param("bucketId") Long bucketId,
        @Param("expenseTypeId") Long expenseTypeId,
        @Param("status") Integer status
    );
} 