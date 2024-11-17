package com.ms.myapp.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private Integer cardId;

    @Column(name="card_holder_name")
    private String cardHolderName;

    @Column(name="card_no")
    private String cardNo;

    @Column(name="expiry_date",columnDefinition = "DATE")
    private LocalDate expiryDate;

    @Column(name="pin_hash")
    private String pinHash;

    @Column(name="balance")
    private Double balance;

    @Column(name="card_type")
    private String cardType;

    @Column(name="mpin_hash")
    private String mpinHash;

    @Column(name="status")
    private String status;

    @Column(name = "added_at" ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime addedAT;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private List<Transaction> transactions = new ArrayList<>();





}
