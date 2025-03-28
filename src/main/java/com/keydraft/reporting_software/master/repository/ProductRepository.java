package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.master.model.Product;
import com.keydraft.reporting_software.master.model.Plant;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductNameAndQuarry(String productName, Plant quarry);
}