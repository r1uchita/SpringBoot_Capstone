package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Account;
import com.example.entities.FundTransfer;
import com.example.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	//private AccountDAO dao;
	
	@Autowired
	private AccountRepository repository;
	
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts=(List<Account>) repository.findAll();
		return accounts;
	}
	
	@Override
	public Account getAccount(int accountNumber){
		Optional<Account> findById = repository.findById(accountNumber);
		Account account= findById.get();
		return account;
	}
	
	@Transactional
	@Override
	public String fundTransfer(FundTransfer fundtrans)
	{
		Account acc=null,acc1=null;
		String str=null;
		if(fundtrans.getFromaccountid()!=fundtrans.getToaccountid())
		{
		Optional<Account> findByfromId = repository.findById(fundtrans.getFromaccountid());
		Optional<Account> findbytoId = repository.findById(fundtrans.getToaccountid());
		if(findByfromId.isPresent() && findbytoId.isPresent())
		{
			acc=findByfromId.get();
			acc1=findbytoId.get();
			if(acc!=null && acc1!=null)
			{
				int amt=acc.getBalance();
				if(amt>=fundtrans.getAmount())
				{
					acc.setBalance(amt-(fundtrans.getAmount()));
					acc1.setBalance(acc1.getBalance()+(fundtrans.getAmount()));
					str="SUCCESS";
				}
				else
					str="Insufficient Balance";
			}
		}
		else if(!findByfromId.isPresent())
			str="Account with id:"+fundtrans.getFromaccountid()+ "doesn't exist";
		else
			str="Account with id:"+fundtrans.getToaccountid()+ "doesn't exist";
		}
		else
			str="Account within same accountnumber cannot take place";
		return str;
	}


	/*@Override
	public Account createAccount(Account acc) {
		return repository.save(acc);

	}

	@Override
	public String transactAccount(int fromId,int toId, int amount) {
		Optional<Account> fromaccount= repository.findById(fromId);
		Optional<Account> toaccount= repository.findById(toId);
		if(fromaccount.isPresent() && toaccount.isPresent()) {
			Account from= fromaccount.get();
			Account to= toaccount.get();
			if(from.getBalance()>amount) {
				from.setBalance(from.getBalance()-amount);
				to.setBalance(amount+to.getBalance());
				repository.save(from);
				repository.save(to);
				return "Transaction successful";
			}
			else {
				return "Not enough balance";
			}
		}
		else {
			return "Invalid credentials";
		}
		
	}
	
	@Override
	public Account updateAccount(int accountNumber, Account account) {
		Account updateAccount=null;
		Optional<Account> findById=repository.findById(accountNumber);
		if(findById.isPresent()) {
			updateAccount=findById.get();
			if(updateAccount!=null) {
				updateAccount.setAccountType(account.getAccountType());
				updateAccount.setBalance(account.getAccountNumber());
				repository.save(updateAccount);
			}
		}
		return updateAccount;
	}

	@Override
	public Account deleteAccount(int accountNumber) {
		Account deleteAccount=null;
		Optional<Account> findById=repository.findById(accountNumber);
		if(findById.isPresent()) {
			deleteAccount=findById.get();
			if(deleteAccount!=null) {
				repository.save(deleteAccount);
			}
		}
		return deleteAccount;
	}*/
	

}
