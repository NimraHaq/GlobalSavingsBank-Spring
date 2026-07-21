package com.vault.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private long transId;


    @Column(name = "service_id", length = 20, nullable = false)
    private String serviceId;

    @ManyToOne
    private long cardNo;

    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;
}
