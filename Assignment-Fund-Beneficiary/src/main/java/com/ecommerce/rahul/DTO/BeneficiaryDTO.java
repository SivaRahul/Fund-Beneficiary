package com.ecommerce.rahul.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
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

public class BeneficiaryDTO implements Serializable
{
	 private static final long serialVersionUID = 354675822400537614L;
	 @NotEmpty(message="provide beneficiary name")
	 @Size(min = 5, max = 50) 
	 private String beneficiaryName;
	 
	 @Size(min = 5, max = 13)
	 @NotNull(message="Account Number is Mandatory")
	 @Pattern(regexp = "[0-9]{13}",message = "provide a valid account number") 
	 private String beneficiaryAccountNo;
	 

	 
	 @NotEmpty(message="IFSC Code is Mandatory")
	 @Size(min = 5, max = 15) 
	 private String ifsCode;
}
	 
	