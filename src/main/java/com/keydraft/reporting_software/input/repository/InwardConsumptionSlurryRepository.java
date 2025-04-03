package com.keydraft.reporting_software.input.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.input.dto.InwardConsumptionSlurryDTO;
import com.keydraft.reporting_software.input.model.InwardConsumptionSlurry;

@Repository
public interface InwardConsumptionSlurryRepository extends JpaRepository<InwardConsumptionSlurry, Long> {

    @Query("SELECT new com.keydraft.reporting_software.input.dto.InwardConsumptionSlurryDTO(i.id, i.particulars, q.shortName, i.tonnage, i.month, i.year) FROM InwardConsumptionSlurry i JOIN i.quarry q")
    List<InwardConsumptionSlurryDTO> getAllInwardConsumptionSlurry();

    boolean existsByMonthAndYear(String month, String year);
} 