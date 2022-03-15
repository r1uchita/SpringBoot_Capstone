package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
