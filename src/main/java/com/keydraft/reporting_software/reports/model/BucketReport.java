package com.keydraft.reporting_software.reports.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "master_ledger")
@Data
public class BucketReport {
    @Id
    private Long ledgerId;
    // Other fields as needed, but we don't need to define them all since we're only using this for the query
}