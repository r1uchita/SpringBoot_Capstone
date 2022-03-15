package com.example.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import com.example.entities.Account;
import com.example.entities.FundTransfer;
import com.example.service.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class) 
@WebMvcTest(AccountController.class)
@TestMethodOrder(OrderAnnotation.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountServiceImpl service;
	
	@Test
	@Order(1)
	void getAccounts() throws Exception {
				
		RequestBuilder request;
		
		List<Account> asList = Arrays.asList(
				new Account(1,"savings",500000),
				new Account(2,"current",600000)
				);
				
				System.out.println("Response :"+asList.toString());
		when(service.getAllAccounts()).thenReturn(asList);
		request=MockMvcRequestBuilders
				.get("/api/accounts")
				.accept(MediaType.APPLICATION_JSON);
		
		String expectedResult="[{accountNumber:1,accountType:savings,balance:500000},{accountNumber:2,accountType:current,balance:600000}]";
		MvcResult result =mockMvc.perform(request)
			   .andExpect(status().isOk())
			   .andExpect(content().json(expectedResult))
			   .andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
		
	}
	
	@Test
	@Order(2)
	void getAccountById() throws Exception {
				
		RequestBuilder request;
       Account ac1=new Account(1,"savings",500000);
		System.out.println("Response :"+ac1.toString());
		when(service.getAccount(any(Integer.class))).thenReturn(ac1);
		request=MockMvcRequestBuilders
		.get("/api/accounts/1")
		.accept(MediaType.APPLICATION_JSON);
		String expectedResult="{accountNumber:1,accountType:savings,balance:500000}";
		MvcResult result =mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().json(expectedResult))
		.andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
		
	}
	
	@Test
	@Order(3)
	void Test_Transferfund() throws Exception {

	RequestBuilder request;
	ObjectMapper objectMapper=new ObjectMapper();

	FundTransfer obj=new FundTransfer(1,2,10000);


	when(service.fundTransfer(any(FundTransfer.class))).thenReturn("Success");

	request=MockMvcRequestBuilders
	.put("/api/accounts/transferfund")
	.contentType(MediaType.APPLICATION_JSON)
	.content(objectMapper.writeValueAsString(obj));


	MvcResult result = mockMvc.perform(request).andReturn();

	MockHttpServletResponse response = result.getResponse();

	System.out.println("Response :"+obj.toString());

	Object headerValue = response.getHeaderValue("location");

	//System.out.println("Location :"+headerValue.toString());
	System.out.println("Response :"+response.getContentAsString());


	assertEquals(HttpStatus.OK.value(), response.getStatus());


	}


}
