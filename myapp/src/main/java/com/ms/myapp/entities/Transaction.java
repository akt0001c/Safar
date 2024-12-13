package com.ms.myapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private Integer transactionId;

    @Column(name="transaction_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transactionDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name="transaction_type")
    private  String transactionType;
}
