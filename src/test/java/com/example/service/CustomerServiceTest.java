package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.entities.Account;
import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;
import com.example.service.CustomerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImpl service;
	
	@Mock
	private CustomerRepository repository;
	
	@Test
	@Order(1)
	void test_CreateCustomer() {
		Customer customer=new Customer();
		customer.setFirstName("Rupa");
		customer.setLastName("Awar");
		customer.setEmail("rupa.awar@wipro.com");
		
		
		Customer mockcustomer=new Customer();
		mockcustomer.setCustomerId(6);
		mockcustomer.setFirstName("Deepak");
		mockcustomer.setLastName("Awar");
		mockcustomer.setEmail("deepak.awar@wipro.com");
		
		when(repository.save(any(Customer.class))).thenReturn(mockcustomer);
		Customer cus=service.createCustomer(customer);
		System.out.println(cus.toString());
		verify(repository,atLeastOnce()).save(customer);
		assertEquals(6,cus.getCustomerId());
	}
	
	@Test
	@Order(2)
	void test_getAllCustomers() {
		List<Customer> customerList=new ArrayList<Customer>();
		Set<Account> acc=new HashSet<Account>();
		Account a1=new Account(1,"savings",900000);
		acc.add(a1);
		Account a11=new Account(2,"current",800000);
		acc.add(a11);
		Set<Account> acc1=new HashSet<Account>();
		Account a2=new Account(1,"current",65000);
		acc1.add(a2);
		customerList.add(new Customer(1,"Rupa","Awar","rupa.awar@wipro.com",acc));
		customerList.add(new Customer(2,"Deepak","Awar","deepak.awar@wipro.com",acc1));
		when(repository.findAll()).thenReturn(customerList);
		
		List<Customer> customers = service.getCustomers();
		
		for(Customer customer: customers) {
			System.out.println(customer.getCustomerId()+"\t"+customer.getFirstName()+"\t"+customer.getLastName()+"\t"+customer.getEmail()+"\t");
			Set<Account> a=customer.getAccounts();
			for(Account acco:a)
			{
				System.out.println(acco.getAccountNumber()+"\t"+acco.getAccountType()+"\t"+acco.getBalance());
			}
		}
		assertEquals(2,customers.get(1).getCustomerId());

	}
	
	@Test
	@Order(3)
	void test_getCustomerById() {
		Set<Account> acc=new HashSet<Account>();
		Account a1=new Account(1,"savings",500000);
		acc.add(a1);
		Optional<Customer> customers = Optional.of(new Customer(2,"Deepak","Awar","deepak.awar@wipro.com",acc));
		when(repository.findById(any(Integer.class))).thenReturn(customers);
		Customer customer = service.getCustomer(1);
		Customer savedCustomer=customers.get();
		System.out.println(savedCustomer.getCustomerId()+"\t"+savedCustomer.getFirstName()+"\t"+savedCustomer.getLastName()+"\t"+savedCustomer.getEmail()+"\t");
		Set<Account> acc1=customer.getAccounts();
		for(Account acco:acc1)
		{
			System.out.println(acco.getAccountNumber()+"\t"+acco.getAccountType()+"\t"+acco.getBalance());
		}
		assertEquals(2, customer.getCustomerId());
	}
	
	@Test
	@Order(4)
	void test_updateCustomer() {
	Customer customer=new Customer(1,"Mahesh","Kumar","mahesh.kumar@wipro.com");
	Optional<Customer> optcustomer = Optional.of(customer);
	when(repository.save(any(Customer.class))).thenReturn(customer);
	when(repository.findById(any(Integer.class))).thenReturn(optcustomer);
	Customer updatedCustomer=service.updateCustomer(1, customer);
	assertEquals(1,updatedCustomer.getCustomerId());
	}
	
	
	@Test
	@Order(5)
	void test_deleteCustomer() {
	Customer customer=new Customer(2,"Deepak","Awar","deepak.awar@wipro.com");
	Optional<Customer> optcustomer = Optional.of(customer);
	when(repository.findById(any(Integer.class))).thenReturn(optcustomer);
	Customer deletedcustomer=service.deleteCustomer(2);
	assertEquals(2,deletedcustomer.getCustomerId());
	}
	
	@Test
	@Order(6)
	void test_AddAccount() {
		Customer customer=new Customer();
		customer.setFirstName("Ruchita");
		customer.setLastName("Bhandari");
		customer.setEmail("ruchita.bhandari@wipro.com");
		Set<Account> acc=new HashSet<Account>();
		Account a1=new Account(1,"savings",200000);
		acc.add(a1);
		customer.setAccounts(acc);
		
		Customer mockcustomer=new Customer();
		mockcustomer.setCustomerId(6);
		mockcustomer.setFirstName("Umesh");
		mockcustomer.setLastName("Udata");
		mockcustomer.setEmail("umesh.udata@wipro.com");
		Set<Account> acc1=new HashSet<Account>();
		Account a2=new Account(1,"current",700000);
		acc1.add(a2);
		mockcustomer.setAccounts(acc1);
		
		when(repository.save(any(Customer.class))).thenReturn(mockcustomer);
		Customer cus=service.createCustomer(customer);
		System.out.println(cus.getCustomerId()+"\t"+cus.getFirstName()+"\t"+cus.getLastName()+"\t"+cus.getEmail()+"\t");
		Set<Account> a=cus.getAccounts();
		for(Account acco:a)
		{
			System.out.println(acco.getAccountNumber()+"\t"+acco.getAccountType()+"\t"+acco.getBalance());
		}
		verify(repository,atLeastOnce()).save(customer);
		assertEquals(6,cus.getCustomerId());
	}
	
	
}
