package com.keydraft.reporting_software.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keydraft.reporting_software.master.model.Bucket;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
    boolean existsByBucketName(String bucketName);
} 