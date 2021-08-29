package com.ecommerce.rahul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.rahul.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{

	Beneficiary findByBeneficiaryAccountNoAndCustomerCustomerId(long accountNo,long customerId);
	
}
