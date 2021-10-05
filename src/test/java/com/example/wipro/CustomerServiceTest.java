package com.example.wipro;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.wipro.dao.CustomerDao;
import com.example.wipro.entity.Account;
import com.example.wipro.entity.Customer;
import com.example.wipro.service.CustomerService;

//@RunWith(SpringRunner.class)
//@SpringBootTest()
public class CustomerServiceTest {
	@Autowired
	private CustomerService service;
	@MockBean
	private CustomerDao dao;

	@Test
	public void getfindAll() {
		Customer customer = new Customer();

		customer.setCustomerId(1);
		customer.setCustomerName("Sahil");
		customer.setCustomerAddress("Patna");

		Account account = new Account();
		account.setAccountNumber("S001");
		account.setAccountType("saving");
		account.setBalance(2000);
		List<Account> accountList = new ArrayList<>();

		customer.setAccount(accountList);

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		when(dao.getfindAll()).thenReturn(customerList);
		assertEquals(1, service.getfindAll().size());

	}

	@Test
	public void getfindById() {

		Customer customer = new Customer();

		customer.setCustomerId(1);
		customer.setCustomerName("Sahil");
		customer.setCustomerAddress("Patna");

		Account account = new Account();
		account.setAccountNumber("S001");
		account.setAccountType("saving");
		account.setBalance(2000);
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		customer.setAccount(accountList);

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		when(dao.findById(1)).thenReturn((Customer) Stream.of((customerList)).collect(Collectors.toList()));
		assertEquals(1, service.findById((customerList).size()));
	}

	@Test
	public void create() {

		when(dao.getcreate(1, "sohan", "patna", "saving", "S004", 2000)).thenReturn("New customer added");
		assertEquals("New customer added", service.create(1, "sohan", "patna", "saving", "S004", 2000));
	}

	@Test
	public void getDelete() {
		Customer customer = new Customer();

		customer.setCustomerId(1);
		customer.setCustomerName("Sahil");
		customer.setCustomerAddress("Patna");

		Account account = new Account();
		account.setAccountNumber("S001");
		account.setAccountType("saving");
		account.setBalance(2000);
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		customer.setAccount(accountList);
		assertEquals("deleted Successfully",service.getDelete(1));

	}

}
