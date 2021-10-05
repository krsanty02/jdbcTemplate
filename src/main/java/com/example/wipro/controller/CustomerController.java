package com.example.wipro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wipro.entity.Customer;
import com.example.wipro.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;

	@Autowired
	DataSource ds;

	private Customer customer;

	@GetMapping("/findbyId/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") int id) throws Exception {
		System.out.println("In customer findById Controller");
		customer = service.findById(id);
		return ResponseEntity.ok(customer);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Customer>> getfindAll() throws Exception {
		System.out.println("In findAll Controller");
		List<Customer> customerList = new ArrayList<>();
		customerList = service.getfindAll();
		return ResponseEntity.ok(customerList);
	}

	@PostMapping("/create")
	public ResponseEntity<String> getCreate(@RequestBody Customer customer) throws Exception {
		System.out.println("In customer Controller");
		int id = customer.getCustomerId();
		String name = customer.getCustomerName();
		String address = customer.getCustomerAddress();
		String accountType = customer.getAccount().get(0).getAccountType();
		String accountNumber = customer.getAccount().get(0).getAccountNumber();
		int balance = customer.getAccount().get(0).getBalance();
		System.out.println(name + address + id + accountType + accountNumber + balance);
		return ResponseEntity.ok(service.create(id, name, address, accountType, accountNumber, balance));
	}

	@GetMapping("/transfer/{accountNbr}/{amount}")

	public ResponseEntity<String> findById(@PathVariable("accountNbr") String accountNbr,
			@PathVariable("amount") int amount) throws Exception {

		service.transfer(accountNbr, amount);
		return ResponseEntity.ok("Success");
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<String> getDeleteById(@PathVariable("id") int id) {

		return ResponseEntity.ok(service.getDelete(id));

	}
}
