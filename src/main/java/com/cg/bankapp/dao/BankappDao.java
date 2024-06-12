package com.cg.bankapp.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.bankapp.entity.Account;


/**
 * BankappDao interface extends JpaRepository for Account entity.
 * This interface provides generic implementations for basic CRUD operations
 * (Create, Read, Update, Delete) on the Account entity using Spring Data JPA.
 * 
 * @param <Account> The entity type that this repository manages, in this case, Account.
 * @param <Integer> The type of the primary key of the entity, in this case, Integer.
 * 
 * @see JpaRepository
 */
public interface BankappDao extends JpaRepository<Account, Integer>{

}
