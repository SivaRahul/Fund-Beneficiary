package com.ecommerce.rahul.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOpeningRequestDTO implements Serializable {
	private static final long serialVersionUID = 3994192272098493497L;

	@NotEmpty(message = "UserName is Mandatory")
	@Size(min = 5, max = 50)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String userName;

	@NotEmpty(message = "FirstName is Mandatory")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$")
	private String firstName;

	@NotEmpty(message = "LastName is Mandatory")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$")
	private String lastName;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@NotEmpty(message = "Provide date of birth ")
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",message = "Provide date of birth (yyyy-MM-dd) format" )
	private String dateOfBirth;

	@NotEmpty(message = "Gender is Mandatory")
	private String gender;

	@NotNull(message = "Mobile is Mandatory")
	@Pattern(regexp = "[0-9]{10}")
	private String mobileNo;

	@NotEmpty(message = "Email-Id is Mandatory")
	@Email
	private String emailId;

	@NotEmpty(message = "Pan Number is Mandatory")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "provide valid pan number")
	private String panCardNo;

	@NotNull(message = "Aadhar Number is Mandatory")
	@Pattern(regexp = "[0-9]{12}")
	private String aadhaarCardNo;


	@NotNull(message = "Enter Opening Deposite Amount")
	private String openingDeposit;

}
