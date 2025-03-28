package com.keydraft.reporting_software.input.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.input.dto.VsiHoursDTO;
import com.keydraft.reporting_software.input.model.VsiHours;

@Repository
public interface VsiHoursRepository extends JpaRepository<VsiHours, Long> {
    @Query("SELECT new com.keydraft.reporting_software.input.dto.VsiHoursDTO(v.id, q.shortName, v.vsiHours) FROM VsiHours v join v.quarry q")
    List<VsiHoursDTO> getAllVsiHours();

    boolean existsByMonthAndYear(String month, String year);
}