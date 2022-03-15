package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entities.Account;
import com.example.entities.FundTransfer;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	public String fundTransfer(FundTransfer fundtrans);
	public  Account getAccount(int accountNumber); 
}