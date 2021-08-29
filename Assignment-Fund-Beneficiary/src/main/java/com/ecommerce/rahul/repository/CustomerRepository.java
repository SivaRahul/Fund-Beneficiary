package com.ecommerce.rahul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.rahul.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	Customer findByUserName(String userName);
	
	Customer findByCustomerId(long customerId);
}
