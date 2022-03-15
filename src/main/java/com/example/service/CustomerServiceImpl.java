package com.example.service;

import java.util.Iterator;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.NotFoundException;
import com.example.entities.Account;
import com.example.entities.Customer;
import com.example.repositories.AccountRepository;
import com.example.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private AccountRepository accountrepository;
	

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers=(List<Customer>) repository.findAll();
		return customers;
	}
	
	@Override
	public Customer getCustomer(int customerId) {
		Optional <Customer> findById = repository.findById(customerId);
		Customer cus = findById.get();
		return cus;
	}


	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(int customerId, Customer customer) {
		Customer updateCustomer=null;
		Optional<Customer> findById=repository.findById(customerId);
		if(findById.isPresent()) {
			updateCustomer=findById.get();
			if(updateCustomer!=null) {
				updateCustomer.setFirstName(customer.getFirstName());
				updateCustomer.setLastName(customer.getLastName());
				updateCustomer.setEmail(customer.getEmail());;
				repository.save(updateCustomer);
			}
		}
		return updateCustomer;
	}

	@Override
	public Customer deleteCustomer(int customerId) {
		Customer deletedCustomer=null;
		Optional<Customer> findById = repository.findById(customerId);
		if(findById.isPresent()) {
			deletedCustomer = findById.get();
			if(deletedCustomer!=null) {
				repository.delete(deletedCustomer);				
			}	
		}
		return deletedCustomer;
	}
    
	/*@Override
	public Customer deleteCustomer(int customerId) {
		Customer deleteCustomer=null;
		Optional<Customer> findById=repository.findById(customerId);
		if(findById.isPresent()) {
			deleteCustomer=findById.get();
			if(deleteCustomer!=null) {
				repository.save(deleteCustomer);
			}
		}
		return deleteCustomer;
	}*/

	public Account jointcase(int accountid)
	{
		try
		{
			Account ac1=accountrepository.findById(accountid).get();
			return ac1;
		}
		catch(Exception e)
		{
			return null;
		}	
	}
	
	@Override
	public Customer addaccount(int customerId, Account account) {
		Customer createCustomer=null;
		Optional<Customer> findById = repository.findById(customerId);
		if(findById.isPresent()) {
			createCustomer = findById.get();
			if(createCustomer!=null) {
				if(!account.getAccountType().equals("joint"))
				{
					int id=account.getAccountNumber();
					Account ac1=jointcase(id);
					if(ac1!=null)
					{
						throw new NotFoundException("Account with id="+id+" already exists");
					}
				}
				Set<Account> acc=createCustomer.getAccounts();
				acc.add(account);
				createCustomer.setAccounts(acc);
				repository.save(createCustomer);
				
			}
			
			
		}
		return createCustomer;
	}
	

	/*private CustomerDAO dao;

	@Override
	public Customer getCustomer(int customerId) {
		Customer customer = dao.getCustomer(customerId);
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return dao.getCustomers();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return dao.createCustomer(customer);
	}

	@Override
	public Customer updateCustomer(int customerId, Customer customer) {
		// TODO Auto-generated method stub
		return dao.updateCustomer(customerId, customer);
	}

	@Override
	public Customer deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		return dao.deleteCustomer(customerId);
	}*/
}
