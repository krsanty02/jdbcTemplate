package com.example.wipro.entity;

import java.util.List;

public class CustomerAccount {
	private List<Customer> customer;
	private List<Account> account;
	
	
	public CustomerAccount() {
		super();
	}
	public CustomerAccount(List<Customer> customer, List<Account> account) {
		super();
		this.customer = customer;
		this.account = account;
	}
	public List<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}

	
}
