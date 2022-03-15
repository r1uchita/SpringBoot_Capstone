package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.FundTransfer;
import com.example.common.NotFoundException;
import com.example.entities.Account;
import com.example.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired	
	private AccountService service;

	// GET /api/accounts
	@GetMapping("/api/accounts")
	public List<Account> getAllAccounts() {
		List<Account> accounts = service.getAllAccounts();
		return accounts;
	}
	
	//PUT /api/accounts/transferfund
	@PutMapping("/api/accounts/transferfund")
	public String fundTransfer(@RequestBody FundTransfer fundtransfer) {
		String str = service.fundTransfer(fundtransfer);
		return str;
	}
		
	// GET /api/accounts/{accountId}
	@RequestMapping(value="/api/accounts/{accountId}",method=RequestMethod.GET)
	public Account getAccount(@PathVariable int accountNumber) {
		Account account = service.getAccount(accountNumber);
		if(account==null) {
			throw new NotFoundException("Customer with accountnumber="+accountNumber+" doesn't exists");
		}
		return account;
	}	
}
	







	/*
	// GET /api/accounts/{accountNumber}
		@RequestMapping(value="/api/accounts/{accountNumber}",method=RequestMethod.GET)
		public Optional<Account> getAccount(@PathVariable int accountNumber) {
			Optional<Account> account = service.getAccount(accountNumber);
			if(account==null) {
				throw new AccountNotFoundException("Account with number="+accountNumber+"  doesn't exist");
				//System.out.println("Employee with id="+customerId+"  doesn't exist");

			}
			return account;
		}

		// POST /api/accounts
		@RequestMapping(value="/api/accounts",method=RequestMethod.POST)	
		public ResponseEntity<Account> createAccount(@RequestBody Account acc) {
			Account createAccount= service.createAccount(acc);
			return new ResponseEntity<Account>(createAccount,HttpStatus.CREATED);

		}

		//PUT /api/accounts/{accountNumber}
		@PutMapping("/api/accounts/{accountNumber}")
		public Account updateAccount(@PathVariable int accountNumber,@RequestBody Account account ) {
			Account updateAccount = service.updateAccount(accountNumber, account);
			if(updateAccount==null) {
				throw new AccountNotFoundException("Account with id="+accountNumber+"  doesn't exist");
			}
			return updateAccount;

		}


		//Delete /api/accounts/{accountNumber}
		@DeleteMapping("/api/accounts/{accountNumber}")
		public Account deleteAccount(@PathVariable int accountNumber) {
			Account deleteAccount = service.deleteAccount(accountNumber);
			if(deleteAccount==null) {
				throw new AccountNotFoundException("Account with id ="+ accountNumber +"  doesn't exist");
			}
			return deleteAccount;

		}*/
