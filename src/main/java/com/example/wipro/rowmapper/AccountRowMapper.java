package com.example.wipro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.wipro.entity.Account;

public class AccountRowMapper implements RowMapper<Account> {
	

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

		System.out.println("coming in AccountrowMapper");
		Account account = new Account();
		try {
			account.setAccountNumber(rs.getString("account_number"));
			account.setAccountType(rs.getString("account_type"));
			account.setBalance(rs.getInt("balance"));
		} catch (Exception e) {
			System.out.println("exception in AccountrowMapper");
			System.out.println(e.getMessage());
		}
		return account;
	}

}
