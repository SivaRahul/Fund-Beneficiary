package com.ecommerce.rahul.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.rahul.DTO.AccountOpeningRequestDTO;
import com.ecommerce.rahul.DTO.BeneficiaryDTO;
import com.ecommerce.rahul.DTO.CredentialCheckDTO;
import com.ecommerce.rahul.DTO.AmountTransferDto;
import com.ecommerce.rahul.exception.UserNameAlreadyExistsException;
import com.ecommerce.rahul.model.Customer;
import com.ecommerce.rahul.model.CustomerCredentials;
import com.ecommerce.rahul.service.BankingServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value ="/banking")
@EnableTransactionManagement
public class BankOperationsController
{
	private static final Logger logger = LoggerFactory.getLogger(BankOperationsController.class);
	
	@Autowired
	BankingServices bankService;
	
    @PostMapping("/")
	public ResponseEntity<?> customerAccountOpening(@Valid @RequestBody AccountOpeningRequestDTO request)  throws UserNameAlreadyExistsException
	{
    	logger.info("inside customerAccountOpening");
		return new ResponseEntity<>(bankService.saveCustomerDetails(request),HttpStatus.OK);
	}
    
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomerDetails()  throws UserNameAlreadyExistsException
	{
		return new ResponseEntity<List<Customer>>(bankService.getAllCustomerDetails(),HttpStatus.OK);
	}
    
    @PostMapping("/{userName}")
	public ResponseEntity<?> saveBeneficiaryDetails(@Valid @RequestBody BeneficiaryDTO request,@PathVariable("userName") String userName)  
	{
    	logger.info("inside saveBeneficiaryDetails");
		return new ResponseEntity<>(bankService.saveBeneficiary(request,userName),HttpStatus.OK);
	}
    
    @PostMapping("/checkCredentials")
	public ResponseEntity<?> checkLoginCredentials(@Valid @RequestBody CredentialCheckDTO credentialCheck)  
	{
    	logger.info("inside checkCredentials");
		return new ResponseEntity<>(bankService.checkLoginCredential(credentialCheck),HttpStatus.OK);
	}
    @PostMapping("/fundTransfer/{userName}")
   	public ResponseEntity<?> fundTransfer(@Valid @RequestBody AmountTransferDto fundTransferDto,@PathVariable("userName") String userName)  
   	{
       	logger.info("inside Fund Transfer");
   		return new ResponseEntity<>(bankService.fundTransfer(fundTransferDto,userName),HttpStatus.OK);
   	}
   
    
    
    
}
