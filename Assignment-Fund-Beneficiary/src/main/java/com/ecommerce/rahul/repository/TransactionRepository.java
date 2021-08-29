package com.ecommerce.rahul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.rahul.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
