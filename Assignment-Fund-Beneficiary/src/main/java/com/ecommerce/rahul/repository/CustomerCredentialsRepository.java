package com.ecommerce.rahul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.rahul.model.CustomerCredentials;

public interface CustomerCredentialsRepository extends JpaRepository<CustomerCredentials, Long>{

	String findByUserPassword(String password);
	
	CustomerCredentials findByUserNameAndUserPassword(String userName,String userPassword);
}
