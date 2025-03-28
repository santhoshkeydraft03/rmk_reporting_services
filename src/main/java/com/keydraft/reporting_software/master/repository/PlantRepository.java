package com.keydraft.reporting_software.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.common.enums.PlantType;
import com.keydraft.reporting_software.master.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    boolean existsByPlantName(String plantName);

    List<Plant> findByPlantType(PlantType plantType);
}