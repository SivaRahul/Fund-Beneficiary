package com.ecommerce.rahul.DTO;

import javax.validation.constraints.NotNull;
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
public class AmountTransferDto 
{
	 
	 @NotNull(message="Enter From Account Number")
	 private String fromAccountNo;
	 

	 @NotNull(message="Enter To Account Number")
	 private String toAccountNo;
	 
	 @NotNull(message="Enter Amount to be Transfered")
     private String transferAmount;
	 
}
