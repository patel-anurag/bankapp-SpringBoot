package com.cg.bankapp.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

/**
 * Represents a bank account entity.
 * This class is annotated with the @Entity annotation, indicating that it is a
 * JPA entity
 * and can be persisted to a relational database. It is also annotated
 * with @Table(name="Account"),
 * specifying the name of the database table to which this entity is mapped.
 */
@Entity
@Table(name = "Account")
public class Account {

	@Id
	private int accountNo; // Account number

	private double accountBalance; // Account balance

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cId")
	private Customer customer; // Associated customer entity

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Transaction> transaction; // List of associated transaction entities

	/**
	 * 
	 * Default constructor. Creates a new instance of Account and initializes the
	 * customer and transaction fields.
	 */
	public Account() {
		customer = new Customer();
		transaction = new ArrayList<>();
	}

	/**
	 * 
	 * Constructor with parameters. Creates a new instance of Account with the
	 * provided account number, account balance, customer, and list of transactions.
	 * 
	 * @param accountNo      The account number.
	 * @param accountBalance The account balance.
	 * @param customer       The associated customer entity.
	 * @param transaction    The list of associated transaction entities.
	 */
	public Account(int accountNo, double accountBalance, Customer customer, List<Transaction> transaction) {
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
		this.customer = customer;
		this.transaction = transaction;
	}

	/**
	 * 
	 * Get the account number.
	 * 
	 * @return The account number.
	 */
	public int getAccountNo() {
		return accountNo;
	}

	/**
	 * 
	 * Set the account number.
	 * 
	 * @param accountNo The account number to set.
	 */
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 
	 * Get the account balance.
	 * 
	 * @return The account balance.
	 */
	public double getAccountBalance() {
		return accountBalance;
	}

	/**
	 * 
	 * Set the account balance.
	 * 
	 * @param accountBalance The account balance to set.
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * 
	 * Get the associated customer entity.
	 * 
	 * @return The associated customer entity.
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * 
	 * Set the associated customer entity.
	 * 
	 * @param customer The customer entity to set.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 
	 * Get the list of associated transaction entities.
	 * 
	 * @return The list of associated transaction entities.
	 */
	public List<Transaction> getTransaction() {
		return transaction;
	}

	/**
	 * 
	 * Set the list of associated transaction entities.
	 * 
	 * @param transaction The list of transaction entities to set.
	 */
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	/**
	 * 
	 * Overrides the default toString() method to return a string representation of
	 * the Account object.
	 * 
	 * @return A string representation of the Account object.
	 */
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountBalance=" + accountBalance + ", customer=" + customer
				+ ", transaction=" + transaction + "]";
	}
}