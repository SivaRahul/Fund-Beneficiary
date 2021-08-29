package com.ecommerce.rahul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.rahul.model.Account;



public interface AccountRepository extends JpaRepository<Account, Long>{

	Long findByAccountNo(long accountNo);
	Account findByAccountNoAndCustomerCustomerId(long accountNo,long customerId);

}
