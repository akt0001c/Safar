package com.safar.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transactions {

 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private Integer transactionId;
 
 @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
 private LocalDateTime transactionDate;
 
 @DecimalMin("0.0")
 private Float amount;
 
 @Enumerated(EnumType.STRING)
 private TransactionType type;


}
