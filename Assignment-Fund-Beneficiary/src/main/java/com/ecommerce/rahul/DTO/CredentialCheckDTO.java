package com.ecommerce.rahul.DTO;

import javax.validation.constraints.NotNull;

public class CredentialCheckDTO {

	 @NotNull(message="Enter UserName")
	 private String userName;
	 

	 @NotNull(message="Enter PassWord")
	 private String userPassword;


	 
	public CredentialCheckDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

}
