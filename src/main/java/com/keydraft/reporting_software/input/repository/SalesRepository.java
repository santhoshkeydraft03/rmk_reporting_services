package com.keydraft.reporting_software.input.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.input.dto.SalesDTO;
import com.keydraft.reporting_software.input.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT new com.keydraft.reporting_software.input.dto.SalesDTO(s.salesId, p.productName, q.shortName, s.salesInTons, s.salesInValue, s.productionStatus, s.billingStatus, s.paymentType, s.gstValue) FROM Sales s join s.product p join s.quarry q")
    List<SalesDTO> getAllSales();
    
    boolean existsByMonthAndYear(String month, String year);
}