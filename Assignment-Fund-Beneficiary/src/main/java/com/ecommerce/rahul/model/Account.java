package com.ecommerce.rahul.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_no")
	private Long accountNo;


	@Column(name = "opening_balance")
	private double openingDeposit;

	@Column(name = "available_balance")
	private double availableBalance;

	@Column(name = "creation_datetime")
	private Date creationDate;
	 

	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
