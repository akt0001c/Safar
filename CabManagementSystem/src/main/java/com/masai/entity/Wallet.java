package com.masai.entity;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer walletId;
	
	
	@DecimalMin("0.0")
	private Float balance;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="walletId")
	private List<Transactions> transactions= new ArrayList<>();
}
