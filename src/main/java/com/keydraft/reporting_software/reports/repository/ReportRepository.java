package com.keydraft.reporting_software.reports.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.reports.dto.AverageSalesPriceDTO;
import com.keydraft.reporting_software.reports.dto.ProductionReportDTO;
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
    List<Object[]> getBucketWiseReport(@Param("bucketId") String bucketId, @Param("month") String month,
            @Param("year") String year);

    @Query("""
                SELECT NEW com.keydraft.reporting_software.reports.dto.AverageSalesPriceDTO(
                    pl.plantName,
                    p.productName,
                    CAST(SUM(s.salesInValue) / SUM(s.salesInTons) AS java.math.BigDecimal)
                )
                FROM Sales s
                LEFT JOIN s.product p
                LEFT JOIN s.quarry pl
                WHERE (:plantId = 0 OR pl.id = :plantId)
                AND s.month = :month
                AND s.year = :year
                GROUP BY p.productName, pl.id, pl.plantName
            """)
    List<AverageSalesPriceDTO> getAverageSalesPrice(
            @Param("plantId") Long plantId,
            @Param("month") String month,
            @Param("year") String year);

    @Query("""
                SELECT NEW com.keydraft.reporting_software.reports.dto.ProductionReportDTO(
                    p.productName,
                    pl.shortName,
                    COALESCE((SELECT cs_prev.closingStockInTons
                              FROM ClosingStock cs_prev
                              WHERE cs_prev.product = p
                              AND cs_prev.quarry = pl
                              AND cs_prev.month = :prevMonth
                              AND cs_prev.year = :prevYear), 0.0),
                    COALESCE((SELECT cs_curr.closingStockInTons
                              FROM ClosingStock cs_curr
                              WHERE cs_curr.product = p
                              AND cs_curr.quarry = pl
                              AND cs_curr.month = :month
                              AND cs_curr.year = :year), 0.0),
                    COALESCE((SELECT SUM(s.salesInTons)
                              FROM Sales s
                              WHERE s.product = p
                              AND s.quarry = pl
                              AND s.month = :month
                              AND s.year = :year), 0.0)
                )
                FROM Product p
                JOIN p.quarry pl
                WHERE (:plantId = 0 OR pl.id = :plantId)
                GROUP BY p.productName, pl.shortName, p.id, pl.id
            """)
    List<ProductionReportDTO> getProductionReport(
            @Param("plantId") Long plantId,
            @Param("month") String month,
            @Param("year") String year,
            @Param("prevMonth") String prevMonth,
            @Param("prevYear") String prevYear);

}
