package com.ecommerce.rahul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="customer_credentials")
public class CustomerCredentials
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="custCredentials_id")
	 private Long custCredentialsId;
	 
	 @Column(name="user_name")
	 private String userName;
	 
	 @Column(name="user_password")
	 private String userPassword;

	 
	 @OneToOne
	 @JoinColumn(name="customer_id")
	 private Customer customer;
	 
} 
