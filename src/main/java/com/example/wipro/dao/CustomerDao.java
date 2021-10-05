package com.example.wipro.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.wipro.entity.Account;
import com.example.wipro.entity.Customer;
import com.example.wipro.rowmapper.AccountRowMapper;
import com.example.wipro.rowmapper.CustomerRowMapper;

@Repository
@Transactional
public class CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	DataSource ds;

	public Customer findById(int id) {
		Customer customer = new Customer();
		try {
			System.out.println("coming in findById dao" + id);
			// List<Customer> customerList = new ArrayList<>();
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
			customer = jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), new Object[] { id });
			System.out.println("query executed");
			System.out.println("cusstomerID" + customer.getCustomerId() + "customerName" + customer.getCustomerName()
					+ "customerAdrress" + customer.getCustomerAddress());
			String qyery = "SELECT * FROM ACCOUNT WHERE customer_customer_id=?";
			List<Account> accountList = jdbcTemplate.query(qyery, new AccountRowMapper(), new Object[] { id });
			System.out.println("account query executed");

			Iterator<Account> ie = accountList.iterator();
			while (ie.hasNext()) {
				System.out.println(ie.next());
			}
			customer.setAccount(accountList);
		} catch (DataAccessException e) {

			System.out.println("code" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;

	}

	public List<Customer> getfindAll() {
		System.out.println("coming in getfindAll dao");
		List<Customer> customerList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM CUSTOMER";

			customerList = jdbcTemplate.query(sql, new CustomerRowMapper());
			System.out.println("query executed");

			for (int i = 0; i < customerList.size(); i++) {
				int id = customerList.get(i).getCustomerId();
				System.out.println("id" + id);
				String query = "SELECT *  FROM ACCOUNT WHERE CUSTOMER_CUSTOMER_ID=?";
				List<Account> accountList = new ArrayList<>();
				accountList = jdbcTemplate.query(query, new Object[] { id }, new AccountRowMapper());
				customerList.get(i).setAccount(accountList);
			}

		} catch (DataAccessException e) {

			System.out.println("code" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}

	public String getcreate(int id, String name, String address, String accountType, String accountNumber,
			int balance) {
		try {
			System.out.println("in create dao");
			int result = jdbcTemplate.update(
					"INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS) VALUES(?,?,?)", id, name,
					address);
			System.out.println(result);

			jdbcTemplate.update(
					"INSERT INTO ACCOUNT (account_number,account_type,balance,customer_customer_id) VALUES(?,?,?,?)",
					accountNumber, accountType, balance, id);
			return "New customer added";
		} catch (DataAccessException e) {
			System.out.println("code" + e.getMessage());
			return "Customer creation Failed";
		} catch (Exception e) {
			e.printStackTrace();
			return "Customer creation Failed";
		}

	}

	public void getTransfer(String accountNbr, int amount) {
		try {
			int leftBalance = 0;
			String sql = "Select balance from account where account_number=?";
			int balance = jdbcTemplate.queryForObject(sql, new Object[] { accountNbr }, Integer.class);
			System.out.println("balance" + balance);

			if (balance > amount) {
				leftBalance = balance - amount;
			} else {
				System.out.println("Not enoughbalance");
			}
			System.out.println("leftBalance" + leftBalance);
			String query = "UPDATE ACCOUNT SET BALANCE=? WHERE account_number=?";
			int result = jdbcTemplate.update(query, leftBalance, accountNbr);
			System.out.println("result" + result);
		} catch (DataAccessException e) {

			System.out.println("code" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getDeleteById(int id) {
		String message = "";
		try {
			String sql = "DELETE FROM ACCOUNT WHERE CUSTOMER_CUSTOMER_ID=?";

			//int a = jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);
		int a=jdbcTemplate.update(sql, new Object[]{id});
			System.out.println("quwery executed");
			if (a > 0) {
				String query="DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
				jdbcTemplate.update(query, new Object[]{id});
				System.out.println("quwery executed>>>>");
				message = "deleted Successfully";
				
			} else {
				message = "Not able to delete";
			}

		} catch (DataAccessException e) {

			System.out.println("code" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
