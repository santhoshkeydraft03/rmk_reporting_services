package com.keydraft.reporting_software.input.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.input.dto.ClosingStockDTO;
import com.keydraft.reporting_software.input.model.ClosingStock;

@Repository
public interface ClosingStockRepository extends JpaRepository<ClosingStock, Long> {

    @Query("SELECT new com.keydraft.reporting_software.input.dto.ClosingStockDTO(c.stockId, p.productName, q.shortName, c.closingStockInTons) FROM ClosingStock c JOIN c.product p JOIN c.quarry q")
    List<ClosingStockDTO> getAllClosingStock();

    boolean existsByMonthAndYear(String month, String year);

}