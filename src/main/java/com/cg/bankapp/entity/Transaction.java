package com.cg.bankapp.entity;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Represents a transaction entity in the system.
 * 
 * @Entity: Indicates that this class is a JPA entity and should be mapped to a database table.
 * @Table: Specifies the name of the database table associated with this entity.
 */
@Entity
@Table(name="Transaction")
public class Transaction {
	
	/**
	 * Represents the transaction ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tId;
	
	/**
	 * Represents the amount of the transaction.
	 */
	private double amount;
	
	/**
	 * Represents the type of the transaction.
	 */
	private String type;
	
	/**
	 * Represents the account associated with the transaction.
	 */
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="accountNo")
	private Account account;
	
	/**
	 * Constructs a new empty Transaction object.
	 */
	public Transaction() {
	}
	
	/**
	 * Constructs a new Transaction object with the given transaction ID, amount, and type.
	 * 
	 * @param tId The transaction ID.
	 * @param amount The amount of the transaction.
	 * @param type The type of the transaction.
	 */
	public Transaction(int tId, double amount, String type) {
		this.tId = tId;
		this.amount = amount;
		this.type=type;
	}
	
	// Getters and Setters
	/**
	 * Returns the transaction ID.
	 * 
	 * @return The transaction ID.
	 */
	public int gettId() {
		return tId;
	}
	
	/**
	 * Sets the transaction ID.
	 * 
	 * @param tId The transaction ID.
	 */
	public void settId(int tId) {
		this.tId = tId;
	}
	
	/**
	 * Returns the amount of the transaction.
	 * 
	 * @return The amount of the transaction.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Sets the amount of the transaction.
	 * 
	 * @param amount The amount of the transaction.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns the type of the transaction.
	 * 
	 * @return The type of the transaction.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the transaction.
	 * 
	 * @param type The type of the transaction.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns a string representation of the Transaction object.
	 * 
	 * @return A string representation of the Transaction object.
	 */
	@Override
	public String toString() {
		return "Transaction [tId=" + tId + ", amount=" + amount + ", type=" + type + "]";
	}

}
