package com.ecommerce.rahul.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.rahul.DTO.AccountOpeningRequestDTO;
import com.ecommerce.rahul.DTO.AmountTransferDto;
import com.ecommerce.rahul.DTO.BeneficiaryDTO;
import com.ecommerce.rahul.DTO.CredentialCheckDTO;
import com.ecommerce.rahul.DTO.AmountTransferDto;
import com.ecommerce.rahul.exception.AadharAlreadyExistsException;
import com.ecommerce.rahul.exception.InSufficientBalanceException;
import com.ecommerce.rahul.exception.InvalidCredentialsException;
import com.ecommerce.rahul.exception.PanNumberAlreadyExistsException;
import com.ecommerce.rahul.exception.ResourceNotFoundException;
import com.ecommerce.rahul.exception.SearchResultNotFoundException;
import com.ecommerce.rahul.exception.UserNameAlreadyExistsException;
import com.ecommerce.rahul.model.Account;
import com.ecommerce.rahul.model.Beneficiary;
import com.ecommerce.rahul.model.Customer;
import com.ecommerce.rahul.model.CustomerCredentials;
import com.ecommerce.rahul.model.Transaction;
import com.ecommerce.rahul.repository.AccountRepository;
import com.ecommerce.rahul.repository.BeneficiaryRepository;
import com.ecommerce.rahul.repository.CustomerCredentialsRepository;
import com.ecommerce.rahul.repository.CustomerRepository;
import com.ecommerce.rahul.repository.TransactionRepository;
import com.ecommerce.rahul.response.AccountAcknowledgement;
import com.ecommerce.rahul.response.AccountAcknowledgement;
import com.ecommerce.rahul.utility.RandomGen;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankingServices {

	private static final Logger logger = LoggerFactory.getLogger(BankingServices.class);
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerCredentialsRepository customerCredentialsRepo;
	@Autowired
	BeneficiaryRepository beneficiaryRepo;
	@Autowired
	TransactionRepository transactionRepo;

	@Autowired
	RandomGen randomGen;

	
	public ResponseEntity<AccountAcknowledgement> saveCustomerDetails(AccountOpeningRequestDTO request)
			throws UserNameAlreadyExistsException {
		Date date = new Date();
		if (Optional.ofNullable(customerRepo.findByUserName(request.getUserName())).isPresent())
			throw new UserNameAlreadyExistsException("UserName already Exists");
		if (Optional.ofNullable(customerRepo.findByUserName(request.getAadhaarCardNo())).isPresent())
			throw new AadharAlreadyExistsException("AadharNumber already Exists");
		if (Optional.ofNullable(customerRepo.findByUserName(request.getPanCardNo())).isPresent())
			throw new PanNumberAlreadyExistsException("PanCard Number already Exists");
		Customer customer = new Customer();
		customer.setUserName(request.getUserName());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
		customer.setGender(request.getGender());
		customer.setMobileNo(Long.valueOf(request.getMobileNo()));
		customer.setEmailId(request.getEmailId());
		customer.setAadhaarCardNo(request.getAadhaarCardNo());
		customer.setPanCardNo(request.getPanCardNo());
		customer.setCreationDate(date);
		
		customerRepo.save(customer);	
		String custId= Optional.ofNullable(customer.getCustomerId()).isEmpty() ? "0" :""+customer.getCustomerId();
		Account acct = new Account();
		acct.setOpeningDeposit(Double.valueOf(request.getOpeningDeposit()));
		long accountNo = Long.valueOf((custId+""+randomGen.randomnumber()).substring(0, 12));
		acct.setAccountNo(accountNo);
		acct.setAvailableBalance(Double.valueOf(request.getOpeningDeposit()));
		acct.setCreationDate(date);
		acct.setCustomer(customer);
		accountRepo.save(acct);
		logger.info("Account Details Saved");
		
		CustomerCredentials credentials = new CustomerCredentials();
		credentials.setCustomer(customer);
		credentials.setUserName(customer.getUserName());
		String password  = acct.getCustomer().getUserName().toString() +acct.getAccountId().toString()+randomGen.randomAlphaNumeric(5);
		credentials.setUserPassword(password);
		customerCredentialsRepo.save(credentials);
		logger.info("Customer Credentials Saved");
		String strMsg ;
		strMsg="Account Successfully Created :: "+customer.getFirstName().toUpperCase()+" " + customer.getLastName().toUpperCase()+System.getProperty("line.separator");
		//strMsg=strMsg+"\n";
		strMsg=strMsg+"Your Login Credentials are ::  "+"UserName:: " + credentials.getUserName()+
				"Password:: " + credentials.getUserPassword();
		return new ResponseEntity<AccountAcknowledgement>(new AccountAcknowledgement(strMsg.toString()), HttpStatus.OK);
	}

	public ResponseEntity<String> saveBeneficiary(BeneficiaryDTO dto, String userName) {
		Customer customer = Optional.ofNullable(customerRepo.findByUserName(userName)).orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Name", userName));
		Beneficiary benificary = new Beneficiary();
		benificary.setBeneficiaryAccountNo(Long.valueOf(dto.getBeneficiaryAccountNo()));
		benificary.setBeneficiaryName(dto.getBeneficiaryName());
		benificary.setCustomer(customer);
		beneficiaryRepo.save(benificary);
		return new ResponseEntity<>("Beneficiary added Successfully ", HttpStatus.OK);
	}

	public ResponseEntity<String> checkLoginCredential(CredentialCheckDTO credentials) {
		
		
		if (Optional.ofNullable(
				customerCredentialsRepo.findByUserNameAndUserPassword(credentials.getUserName(), credentials.getUserPassword()))
				.isEmpty()) {
			throw new InvalidCredentialsException("UserName or Password Incorrect ");
		}
		return new ResponseEntity<>("LoggedIn Successfully", HttpStatus.OK);
	}
	
	public ResponseEntity<String> fundTransfer(AmountTransferDto amountTransferDto, String userName) {
		Customer customer = Optional.ofNullable(customerRepo.findByUserName(userName))
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Name", userName));
		
		// Check if the account number exists or not
		Account fromAccDetails = Optional
				.ofNullable(accountRepo.findByAccountNoAndCustomerCustomerId(
						Long.valueOf(amountTransferDto.getFromAccountNo()), customer.getCustomerId()))
				.orElseThrow(() -> new ResourceNotFoundException("Account Details", "Account Number",
						amountTransferDto.getFromAccountNo()));

		Beneficiary toAccDetails = Optional
				.ofNullable(beneficiaryRepo.findByBeneficiaryAccountNoAndCustomerCustomerId(
						Long.valueOf(amountTransferDto.getToAccountNo()), customer.getCustomerId()))
				.orElseThrow(() -> new ResourceNotFoundException("Customers Beneficiary Account Details  ", "Account Number",
						amountTransferDto.getToAccountNo()));

		if (Double.valueOf(amountTransferDto.getTransferAmount()) > fromAccDetails.getAvailableBalance()) {
			throw new InSufficientBalanceException(
					"InSufficent Balance ");
		}


		Date date = new Date();
		Transaction sourceAccount = new Transaction();
		Transaction targetAccount = new Transaction();

		
		Timestamp transactionTime = new Timestamp(date.getTime());
		sourceAccount.setAmount(Double.valueOf(amountTransferDto.getTransferAmount()));
		sourceAccount.setFromAccount(fromAccDetails.getAccountNo());
		sourceAccount.setTransactionTime(transactionTime);
		sourceAccount.setTransactionType("Debit");
		sourceAccount.setToAccount(toAccDetails.getBeneficiaryAccountNo());
		transactionRepo.save(sourceAccount);

		// inserting records to transactions tables for target account transaction
		targetAccount.setAmount(Double.valueOf(amountTransferDto.getTransferAmount()));
		targetAccount.setFromAccount(fromAccDetails.getAccountNo());
		targetAccount.setToAccount(toAccDetails.getBeneficiaryAccountNo());
		targetAccount.setTransactionTime(transactionTime);
		targetAccount.setTransactionType("Credit");
		transactionRepo.save(targetAccount);

		// updating the account details for the given account number
		if (Optional.ofNullable(transactionRepo).isPresent()) {
			fromAccDetails.setAvailableBalance(
					fromAccDetails.getAvailableBalance() - Double.valueOf(amountTransferDto.getTransferAmount()));
			accountRepo.save(fromAccDetails);
		}
		if (Optional.ofNullable(transactionRepo).isPresent()) {
			toAccDetails.setBeneficiaryBalance(
					toAccDetails.getBeneficiaryBalance() + Double.valueOf(amountTransferDto.getTransferAmount()));
			beneficiaryRepo.save(toAccDetails);
		}

		return new ResponseEntity<>("Transaction Completed ", HttpStatus.OK);
	}

	public List<Customer> getAllCustomerDetails() {
		List<Customer> customers= customerRepo.findAll();
		if(customers.isEmpty()) {
	    	throw new SearchResultNotFoundException("Search Details Not Found or List is Empty");
	    }
		return customers;
	}

	
	
	
}
