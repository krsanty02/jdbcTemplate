package com.example.wipro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.wipro.entity.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("coming in customerrowMapper");
		Customer customer = new Customer();
		try {

			customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
			customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
			customer.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS"));

		} catch (Exception e) {
			System.out.println("exception in rowMapper");
			System.out.println(e.getMessage());
		}
		return customer;
	}

}