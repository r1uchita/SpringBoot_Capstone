package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.controller.AccountController;
import com.example.controller.CustomerController;
import com.example.entities.Account;
import com.example.entities.Customer;
import com.example.service.AccountServiceImpl;
import com.example.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class) 
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerServiceImpl service;
	
	@MockBean
	private AccountServiceImpl ser;
	
	@Test
	@Order(1)
	void createCustomers() throws Exception {
				
		RequestBuilder request;
		ObjectMapper objectMapper=new ObjectMapper();
		
		Customer customer=new Customer(1,"Ruchita","Bhandari","ruchita.bhandari@wipro.com");
		Customer mockCustomer=new Customer(2,"Rupa","Awar","rupa.awar@wipro.com");
		
			
		when(service.createCustomer(any(Customer.class))).thenReturn(mockCustomer);
		
		request=MockMvcRequestBuilders
				.post("/api/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer));
		
		
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		Object headerValue = response.getHeaderValue("location");
		
		//System.out.println("Location :"+headerValue.toString());
		System.out.println("Response :"+response.getContentAsString());
		
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus()+1);
		
			  
		
		
	}
	
	@Test
	@Order(2)
	void getCustomers() throws Exception {
				
		RequestBuilder request;
		
		List<Customer> customerList=new ArrayList<Customer>();
		Set<Account> acc=new HashSet<Account>();
		Account a1=new Account(1,"savings",500000);
		acc.add(a1);
		Account a11=new Account(2,"current",700000);
		acc.add(a11);
		Set<Account> acc1=new HashSet<Account>();
		Account a2=new Account(1,"current",600000);
		acc1.add(a2);
		customerList.add(new Customer(1,"Ruchita","Bhandari","ruchita.bhandari@wipro.com",acc));
		customerList.add(new Customer(2,"Rupa","Awar","rupa.awar@wipro.com",acc1));
				
				System.out.println("Response :"+customerList.toString());
		when(service.getCustomers()).thenReturn(customerList);
		request=MockMvcRequestBuilders
				.get("/api/customers")
				.accept(MediaType.APPLICATION_JSON);
		
		String expectedResult="[{customerId:1,firstName:Ruchita,lastName:Bhandari,email=ruchita.bhandari@wipro.com,accounts:[{accountNumber:1,accountType:savings,balance:500000},{accountNumber:2,accountType:current,balance:700000}]},{customerId:2,firstName:Rupa,lastName:Awar,email=rupa.awar@wipro.com,accounts:[{accountNumber:1,accountType:current,balance:600000}]}]";
		MvcResult result =mockMvc.perform(request)
			   .andExpect(status().isOk())
			   .andExpect(content().json(expectedResult))
			   .andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
		
	}
	
	@Test
	@Order(3)
	void getCustomerById() throws Exception {
				
		RequestBuilder request;
		Set<Account> acc=new HashSet<Account>();
		Account a1=new Account(1,"savings",500000);
		acc.add(a1);
		Customer cus=new Customer(1,"Ruchita","Bhandari","ruchita.bhandari@wipro.com");
		cus.setAccounts(acc);
		System.out.println("Response :"+cus.toString());
		when(service.getCustomer(any(Integer.class))).thenReturn(cus);
		request=MockMvcRequestBuilders
		.get("/api/customers/1")
		.accept(MediaType.APPLICATION_JSON);



		String expectedResult="{customerId:1,firstName:Ruchita,lastName:Bhandari,email=ruchita.bhandari@wipro.com,accounts:[{accountNumber:1,accountType:savings,balance:500000}]}";

		MvcResult result =mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().json(expectedResult))
		.andReturn();



		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
		
	}
	
	@Test
	@Order(4)
	void Test_updateCustomer() throws Exception {

	RequestBuilder request;
	ObjectMapper objectMapper=new ObjectMapper();

	Customer customer=new Customer(1,"Ruchita","Bhandari","ruchita.bhandari@wipro.com");
	Customer mockCustomer=new Customer(2,"Rupa","Awar","rupa.awar@wipro.com");


	when(service.updateCustomer(any(Integer.class),any(Customer.class))).thenReturn(mockCustomer);

	request=MockMvcRequestBuilders
	.put("/api/customers/1")
	.contentType(MediaType.APPLICATION_JSON)
	.content(objectMapper.writeValueAsString(customer));


	MvcResult result = mockMvc.perform(request).andReturn();
	MockHttpServletResponse response = result.getResponse();
	System.out.println("Response :"+customer.toString());
	Object headerValue = response.getHeaderValue("location");

	//System.out.println("Location :"+headerValue.toString());
	System.out.println("Response :"+response.getContentAsString());


	assertEquals(HttpStatus.OK.value(), response.getStatus());


	}

	@Test
	@Order(5)
	void Test_deleteCustomer() throws Exception {

	RequestBuilder request;
	ObjectMapper objectMapper=new ObjectMapper();

	Customer customer=new Customer(1,"Ruchita","Bhandari","ruchita.bhandari@wipro.com");


	when(service.deleteCustomer(any(Integer.class))).thenReturn(customer);

	request=MockMvcRequestBuilders
	.delete("/api/customers/1")
	.contentType(MediaType.APPLICATION_JSON)
	.content(objectMapper.writeValueAsString(customer));


	MvcResult result = mockMvc.perform(request).andReturn();

	MockHttpServletResponse response = result.getResponse();

	System.out.println("Response :"+customer.toString());

	Object headerValue = response.getHeaderValue("location");

	//System.out.println("Location :"+headerValue.toString());
	System.out.println("Response :"+response.getContentAsString());


	assertEquals(HttpStatus.OK.value(), response.getStatus());


	}

	@Test
	@Order(6)
	void test_addaccount() throws Exception {



	RequestBuilder request;

	ObjectMapper objectMapper=new ObjectMapper();

	Set<Account> set=new HashSet<>();
	Account ac1=new Account(1,"savings",500000);
	set.add(ac1);
	Customer customer=new Customer(1,"Ruchita","Bhandari","ruchita.bhandari@wipro.com",set);

	when(service.addaccount(any(Integer.class),any(Account.class))).thenReturn(customer);
	System.out.println("Response :"+customer.toString());

	request=MockMvcRequestBuilders
	.post("/api/customers/1/accounts")
	.contentType(MediaType.APPLICATION_JSON)
	.content(objectMapper.writeValueAsString(ac1));

	MvcResult result = mockMvc.perform(request).andReturn();



	MockHttpServletResponse response = result.getResponse();
	Object headerValue = response.getHeaderValue("location");

	//System.out.println("Location :"+headerValue.toString());
	System.out.println("Response :"+response.getContentAsString());


	assertEquals(HttpStatus.CREATED.value(), response.getStatus()+1);



	}



}
