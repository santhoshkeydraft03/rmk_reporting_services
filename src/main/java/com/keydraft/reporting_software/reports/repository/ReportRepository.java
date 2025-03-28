package com.keydraft.reporting_software.reports.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.reports.model.BucketReport;

@Repository
public interface ReportRepository extends JpaRepository<BucketReport, Long> {
    
    @Query(value = """
            SELECT 
                c.bucketName,
                d.name, 
                e.expenseTypeName, 
                SUM(l.amount),l.month, l.year
            FROM LedgerEntry l
            LEFT JOIN l.ledger a
            LEFT JOIN a.bucket c 
            LEFT JOIN a.expenseGroup d 
            LEFT JOIN a.expenseType e 
            WHERE c.bucketId = :bucketId
            AND l.month = :month
            AND l.year = :year
            GROUP BY d.name, e.expenseTypeName, c.bucketName, l.month, l.year
            """)
    List<Object[]> getBucketWiseReport(@Param("bucketId") String bucketId, @Param("month") String month, @Param("year") String year);


    
}
