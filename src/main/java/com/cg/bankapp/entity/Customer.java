package com.cg.bankapp.entity;

import javax.persistence.*;

/**
 * Represents a customer entity in the system.
 * 
 * @Entity: Indicates that this class is a JPA entity and should be mapped to a database table.
 * @Table: Specifies the name of the database table associated with this entity.
 */
@Entity
@Table(name="Customer")
public class Customer {
	
	/**
	 * Represents the first name of the customer.
	 */
	@Column(name="first_name",nullable=false,unique=false)
	private String firstName;
	
	/**
	 * Represents the last name of the customer.
	 */
	@Column(name="last_name",nullable=false,unique=false)
	private String lastName;
	
	/**
	 * Represents the customer ID.
	 */
	@Id
	private int cId;
	
	/**
	 * Represents the account associated with the customer.
	 */
	@OneToOne
	@JoinColumn(name="cId")
	private Account account;
	
	/**
	 * Constructs a new Customer object with the given first name, last name, and customer ID.
	 * 
	 * @param firstName The first name of the customer.
	 * @param lastName The last name of the customer.
	 * @param cId The customer ID.
	 */
	public Customer(String firstName, String lastName, int cId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.cId = cId;
	}
	
	/**
	 * Constructs a new empty Customer object.
	 */
	public Customer() {
	}
	
	// Getters and Setters
	/**
	 * Returns the first name of the customer.
	 * 
	 * @return The first name of the customer.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the customer.
	 * 
	 * @param firstName The first name of the customer.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns the last name of the customer.
	 * 
	 * @return The last name of the customer.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the customer.
	 * 
	 * @param lastName The last name of the customer.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns the customer ID.
	 * 
	 * @return The customer ID.
	 */
	public int getcId() {
		return cId;
	}
	
	/**
	 * Sets the customer ID.
	 * 
	 * @param cId The customer ID.
	 */
	public void setcId(int cId) {
		this.cId = cId;
	}

	/**
	 * Returns a string representation of the Customer object.
	 * 
	 * @return A string representation of the Customer object.
	 */
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", cId=" + cId + "]";
	}
}
