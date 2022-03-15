package com.example.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
