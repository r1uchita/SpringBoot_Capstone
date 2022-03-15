package com.example.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entities.Customer;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;
	
	@Test
	@Order(1)
	void createCustomer() {
		System.out.println("Create Customer...");
		Customer c1=new Customer();
		Customer c2=new Customer();

		c1.setFirstName("Ruchita");
		c1.setLastName("Bhandari");
		c1.setEmail("ruchita.bhandari@wipro.com");
		

		c2.setFirstName("Ashwini");
		c2.setLastName("Bhandari");
		c2.setEmail("ashwini.bhandari@wipro.com");
		Customer saveCustomer1 = repository.save(c1);
		Customer saveCustomer2 = repository.save(c2);
		assertEquals(1,saveCustomer1.getCustomerId());
		assertEquals(2, saveCustomer2.getCustomerId());
		
	}
	
	@Test
	@Order(2)
	void getCustomers() {
		Customer c1=new Customer();
		Customer c2=new Customer();

		c1.setFirstName("Deepak");
		c1.setLastName("Bhandai");
		c1.setEmail("ruchita.bhandari@wipro.com");
		

		c2.setFirstName("Ashwini");
		c2.setLastName("Bhandari");
		c2.setEmail("ashwini.bhandari@wipro.com");
		repository.save(c1);
		repository.save(c2);
		
		List<Customer> customers = (List<Customer>) repository.findAll();
		
		System.out.println("Test 2: Customer Details");
		
		for(Customer customer: customers) {
			System.out.println(customer.getCustomerId()+"\t"+customer.getFirstName()+"\t"+customer.getLastName()+"\t"+customer.getEmail());
		}
		assertEquals(4, customers.size());
		
	}
	
	/*@Test
	@Order(1)
	void saveCustomer() {
		System.out.println("Save Customer");
		Customer customer=new Customer("Rupa","awar","rupaawar@gmail.com");
		repository.save(customer);
		
		customer=new Customer("charu","Bhandari","charubhandari@gmail.com");
		repository.save(customer);
		customer=new Customer("Deepak","Awar","deepakawar8@gmail.com");
		repository.save(customer);
		customer=new Customer("Umesh","Udata","umesh.udata@gmail.com");
		repository.save(customer);
		customer=new Customer("Mahesh","Nagmal","nagmal.mahesh@gmail.com");
		repository.save(customer);
	}
	@Test
	@Order(2)
	void findCustomer() {
		System.out.println("Find Customer");
		Optional<Customer> findById=repository.findById(2);
		Customer customer=findById.orElse(null);
		System.out.println("Customer Details:"+customer);
	}
	@Test
	@Order(5)
	void findCustomers() {
		System.out.println("Find Customers");
		Iterable<Customer> customers =repository.findAll();
		for(Customer customer:customers) {
			System.out.println("Customer Details:"+customer);
		}
	}
	
	@Test
	@Order(3)
	void updateCustomer() {
		System.out.println("Update Customer");
		Customer customer=null;
		Optional<Customer> findById=repository.findById(2);
		if(findById.isPresent()) {
			customer=findById.get();
			if(customer!=null) {
				customer.setFirstName("ruchita");
				customer.setLastName("bhandari");
				customer.setEmail("ruchitabhandari@wipro.com");
			}
			repository.save(customer);
		}
		
	}
	
	@Test
	@Order(4)
	void deleteCustomer() {
		System.out.println("Delete Customer");
		Customer customer=null;
		Optional<Customer> findById=repository.findById(4);	
		if(findById.isPresent()) {
			customer=findById.get();
			repository.delete(customer);
		}
	}	*/
	
}
