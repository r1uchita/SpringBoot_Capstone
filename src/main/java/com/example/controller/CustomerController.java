package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.NotFoundException;
import com.example.entities.Account;
import com.example.entities.Customer;
import com.example.service.CustomerService;


@RestController
public class CustomerController{

	@Autowired	
	private CustomerService service;
	
	//private CustomerRepository repository;

	// GET /api/customers
	@GetMapping("/api/customers")
	public List<Customer> getCustomers() {
		List<Customer> customers = service.getCustomers();
		return customers;
	}
	

	// GET /api/customers/{customerId}
		@RequestMapping(value="/api/customers/{customerId}",method=RequestMethod.GET)
		public Customer getCustomer(@PathVariable int customerId) {
			Customer customer = service.getCustomer(customerId);
			if(customer==null) {
				throw new NotFoundException("Customer with id="+customerId+" doesn't exists");
			}
			return customer;
			
		}
		
	// POST /api/customers
		@RequestMapping(value="/api/customers",method=RequestMethod.POST)	
		public Customer createCustomer(@RequestBody Customer customer) {
			Customer createCustomer = service.createCustomer(customer);
			return createCustomer;
	}
		
	// POST /customers/{customerId}/accounts
		@RequestMapping(value="/api/customers/{customerId}/accounts",method=RequestMethod.POST)	
		public Customer addAccount(@PathVariable int customerId,@RequestBody Account account) {
			Customer createCustomer = service.addaccount(customerId,account);
			if(createCustomer==null) {
				throw new NotFoundException("Customer with id="+customerId+" doesn't exists");
			}
			else
			return createCustomer;
			
		}

	//PUT /api/customers/{customerId}
	@PutMapping("/api/customers/{customerId}")
	public Customer updateCustomer(@PathVariable int customerId,@RequestBody Customer customer ) {
		Customer updateCustomer = service.updateCustomer(customerId, customer);
		if(updateCustomer==null) {
			throw new NotFoundException("Customer with id="+customerId+"  doesn't exist");
		}
		return updateCustomer;

	}

	//Delete /api/customers/{customerId}
	@DeleteMapping("/api/customers/{customerId}")
	public Customer deleteCustomer(@PathVariable int customerId) {
		Customer deleteCustomer = service.deleteCustomer(customerId);
		if(deleteCustomer==null) {
			throw new NotFoundException("Customer with id ="+ customerId +"  doesn't exist");
		}
		return deleteCustomer;

	}

}
