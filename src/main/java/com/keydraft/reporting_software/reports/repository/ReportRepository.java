package com.keydraft.reporting_software.reports.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.reports.dto.AverageSalesPriceDTO;
import com.keydraft.reporting_software.reports.dto.ProductionReportDTO;
import com.keydraft.reporting_software.input.dto.VsiHoursDTO;
import com.keydraft.reporting_software.reports.dto.AverageCostDTO;
import com.keydraft.reporting_software.reports.model.BucketReport;

@Repository
public interface ReportRepository extends JpaRepository<BucketReport, Long> {

    // ______________________ BUCKET WISE REPORT ______________________
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

    // ______________________ AVERAGE SALES PRICE REPORT ______________________
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

    // ______________________ PRODUCTION REPORT ______________________
    @Query("""
                SELECT NEW com.keydraft.reporting_software.reports.dto.ProductionReportDTO(
                    p.productName,
                    pl.shortName,
                    COALESCE((SELECT cs_prev.closingStockInTons
                              FROM ClosingStock cs_prev
                              WHERE cs_prev.product = p
                              AND cs_prev.quarry = pl
                              AND cs_prev.month = :prevMonth
                              AND cs_prev.year = :year), 0.0),
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
                AND p.productGroup = com.keydraft.reporting_software.common.enums.ProductGroup.PRODUCT
                GROUP BY p.productName, pl.shortName, p.id, pl.id
            """)
    List<ProductionReportDTO> getProductionReport(
            @Param("plantId") Long plantId,
            @Param("month") String month,
            @Param("year") String year,
            @Param("prevMonth") String prevMonth,
            @Param("prevYear") String prevYear);

    // ______________________ AVERAGE COST REPORT ______________________

    @Query("""
                SELECT NEW com.keydraft.reporting_software.reports.dto.AverageCostDTO(
                    pl.shortName,
                    CAST((SELECT COALESCE(SUM(le.amount), 0.0)
                          FROM LedgerEntry le
                          JOIN le.ledger.bucket b
                          WHERE b.plant = pl
                          AND b.category = com.keydraft.reporting_software.common.enums.Category.PRODUCTION
                          AND le.month = :month
                          AND le.year = :year) AS java.math.BigDecimal),
                    CAST((SELECT COALESCE(SUM(
                            COALESCE(s.salesInTons, 0.0) +
                            COALESCE(cs.closingStockInTons, 0.0) -
                            COALESCE(cs_prev.closingStockInTons, 0.0)
                        ), 0.0)
                        FROM Product p2
                        JOIN p2.quarry pl2
                        LEFT JOIN Sales s ON s.product = p2 AND s.quarry = pl2
                            AND s.month = :month AND s.year = :year
                        LEFT JOIN ClosingStock cs ON cs.product = p2
                            AND cs.quarry = pl2
                            AND cs.month = :month
                            AND cs.year = :year
                        LEFT JOIN ClosingStock cs_prev ON cs_prev.product = p2
                            AND cs_prev.quarry = pl2
                            AND cs_prev.month = :month
                            AND cs_prev.year = :year
                        WHERE pl2 = pl
                        AND p2.productGroup = com.keydraft.reporting_software.common.enums.ProductGroup.PRODUCT) AS java.math.BigDecimal)
                )
                FROM Plant pl
                WHERE pl.plantType = com.keydraft.reporting_software.common.enums.PlantType.QUARRY
            """)
    List<AverageCostDTO> getAverageCost(
            @Param("month") String month,
            @Param("year") String year);

    @Query("""
                SELECT pl.shortName,
                       CAST(COALESCE(SUM(le.amount), 0.0) AS java.math.BigDecimal)
                FROM Plant pl
                LEFT JOIN Bucket b ON b.plant = pl
                LEFT JOIN LedgerEntry le ON le.ledger.bucket = b
                    AND le.month = :month
                    AND le.year = :year
                WHERE pl.plantType = com.keydraft.reporting_software.common.enums.PlantType.QUARRY
                AND b.category = com.keydraft.reporting_software.common.enums.Category.PRODUCTION
                GROUP BY pl.shortName, pl.id
            """)
    List<Object[]> getQuarryBucketTotals(
            @Param("month") String month,
            @Param("year") String year);

    // ______________________ MATERIAL COST REPORT ______________________
    @Query("""
            SELECT
                pl.shortName,
                CAST(COALESCE(SUM(cs.closingStockInTons), 0.0) AS java.math.BigDecimal)
            FROM Plant pl
            JOIN Product p ON p.quarry = pl
            LEFT JOIN ClosingStock cs ON cs.product = p
                AND cs.quarry = pl
                AND cs.month = :month
                AND cs.year = :year
            WHERE pl.plantType = 'QUARRY'
            AND p.productGroup IN ('CHAKKAI', 'FILTER_CHAKKAI')
            GROUP BY pl.shortName, pl.id
            """)
    List<Object[]> getChakkaiOpeningStock(
            @Param("month") String month,
            @Param("year") String year);

    @Query("""
            SELECT
                pl.shortName,
                CAST(COALESCE(SUM(le.amount), 0.0) AS java.math.BigDecimal)
            FROM Plant pl
            LEFT JOIN Bucket b ON b.plant = pl
            LEFT JOIN LedgerEntry le ON le.ledger.bucket = b
                AND le.month = :month
                AND le.year = :year
            WHERE pl.plantType = 'QUARRY'
            AND b.category IN ('PRODUCTION', 'TRANSPORT')
            GROUP BY pl.shortName, pl.id
            """)
    List<Object[]> getProductionTransportBucketTotals(
            @Param("month") String month,
            @Param("year") String year);

    @Query("""
            SELECT
                CAST(COALESCE(SUM(le.amount), 0.0) AS java.math.BigDecimal)
            FROM LedgerEntry le
            JOIN le.ledger l
            JOIN l.bucket b
            WHERE b.bucketName = :bucketName
            AND le.month = :month
            AND le.year = :year
            """)
    BigDecimal getCrusherBucketTotal(
            @Param("bucketName") String bucketName,
            @Param("month") String month,
            @Param("year") String year);


            @Query("""
            SELECT new com.keydraft.reporting_software.input.dto.VsiHoursDTO(v.id, q.shortName, v.vsiHours) 
            FROM VsiHours v 
            JOIN v.quarry q
            WHERE v.month = :month 
            AND v.year = :year
            """)
    List<VsiHoursDTO> getVsiHoursByMonthAndYear(
            @Param("month") String month,
            @Param("year") String year);

}
