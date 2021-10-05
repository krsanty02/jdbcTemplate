package com.example.wipro.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String accountNumber;

	private String accountType;
	private int balance;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Customer customer;

	public Account() {
		super();
		
	}

	public Account(String accountNumber, String accountType, int balance, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.customer = customer;
	}

	public Account(String accountNumber, String accountType, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ ", customer=" + customer + "]";
	}

}
