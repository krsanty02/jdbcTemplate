package com.example.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.wipro.dao.CustomerDao;
import com.example.wipro.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;

	private Customer customer;

	public Customer findById(int id) {
		System.out.println("id in service " + id);
		customer = dao.findById(id);
		return customer;

	}

	public List<Customer> getfindAll() {
		System.out.println("service getfindAll");

		List<Customer> customerList = dao.getfindAll();
		return customerList;
	}

	public String create(int id, String name, String address, String accountType, String accountNumber, int balance) {
		System.out.println("in create service");
		try {
			System.out.println("Inside try : Before Callingmethod");
			return dao.getcreate(id, name, address, accountType, accountNumber, balance);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "Exception";
	}

	public void transfer(String accountNbr, int amount) {
		dao.getTransfer(accountNbr, amount);
	}

	public String getDelete(int id) {
		return dao.getDeleteById(id);
		
		
	}
}
