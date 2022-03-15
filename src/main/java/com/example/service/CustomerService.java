package com.example.service;

import java.util.List;
import com.example.entities.Account;
import com.example.entities.Customer;


public interface CustomerService {
	
	public List<Customer> getCustomers();
	public Customer getCustomer(int customerId) ;
	public Customer addaccount(int customerId,Account account);
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(int customerId, Customer customer);
	public Customer deleteCustomer(int customerId);
}
