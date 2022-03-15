package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.entities.Account;
import com.example.entities.FundTransfer;
import com.example.repositories.AccountRepository;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@InjectMocks
	AccountServiceImpl service;
	
	@Mock
	private AccountRepository repository;
	
	@Test
	@Order(1)
	void test_getAllAccounts() {
		List<Account> accountList=new ArrayList<Account>();
		accountList.add(new Account(1,"savings",500000));
		accountList.add(new Account(2,"current",600000));
		when(repository.findAll()).thenReturn(accountList);
		
		List<Account> accounts = service.getAllAccounts();
		
		for(Account account: accounts) {
			System.out.println(account.getAccountNumber()+"\t"+account.getAccountType()+"\t"+account.getBalance());
		}
		assertEquals(2,accounts.get(1).getAccountNumber());

	}
	
	@Test
	@Order(2)
	void test_getAccountById() {
		Optional<Account> accounts = Optional.of(new Account(2,"savings",100000));
		when(repository.findById(any(Integer.class))).thenReturn(accounts);
		Account account = service.getAccount(1);
		Account savedAccount=accounts.get();
		System.out.println(savedAccount.getAccountNumber()+"\t"+savedAccount.getAccountType()+"\t"+savedAccount.getBalance());
		assertEquals(2, account.getAccountNumber());
	}

	@Test
	@Order(3)
	void test_transferfund() {
	Optional<Account> account1 = Optional.of(new Account(1,"savings",500000));
	Optional<Account> account2 = Optional.of(new Account(2,"current",600000));
	when(repository.findById(1)).thenReturn(account1);
	when(repository.findById(2)).thenReturn(account2);
	FundTransfer tf=new FundTransfer(1, 2, 100000);
	String status=service.fundTransfer(tf);
	assertEquals("SUCCESS",status);
	FundTransfer tf1=new FundTransfer(1, 2, 1000000);
	String status2=service.fundTransfer(tf1);
	assertEquals("Insufficient Balance",status2);
	}


}
