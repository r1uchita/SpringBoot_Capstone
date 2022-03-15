package com.example.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entities.Account;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;

	@Test
	@Order(1)
	void createAccount() {
		System.out.println("Create Account");
		Account a1=new Account();
		Account a2=new Account();
        
		a1.setAccountNumber(1001);
		a1.setAccountType("Savings");
		a1.setBalance(20000);

		a2.setAccountNumber(1002);
		a2.setAccountType("Current");
		a2.setBalance(15000);
		
		Account saveAccount1 = repository.save(a1);
		Account saveAccount2 = repository.save(a2);
		
		System.out.print(saveAccount1.getAccountNumber());
		System.out.print(saveAccount2.getAccountNumber());
		assertEquals(1001,saveAccount1.getAccountNumber());
		assertEquals(1002, saveAccount2.getAccountNumber());
		
	}
	
	@Test
	@Order(2)
	void getAccounts() {
		Account a1=new Account();
		Account a2=new Account();

		a1.setAccountNumber(1001);
		a1.setAccountType("Savings");
		a1.setBalance(20000);

		a2.setAccountNumber(1002);
		a2.setAccountType("Current");
		a2.setBalance(15000);
		
		repository.save(a1);
		repository.save(a2);
		
		List<Account> accounts = (List<Account>) repository.findAll();
		
		System.out.println("Test 2: Account Details");
		
		for(Account account: accounts) {
			System.out.println(account.getAccountNumber()+"\t"+account.getAccountType()+"\t"+account.getBalance());
		}
		assertEquals(2, accounts.size());
		
	}
	
}
