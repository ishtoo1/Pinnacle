package com.ishtoo.pinnacle.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.LoginAccount;

@Repository
public class LoginAccountDaoImpl implements LoginAccountDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public boolean doesLoginAccountExists(String username) {
		String sql="select exists (select * from LoginAccount where username=?)";
		return template.queryForObject(sql, Boolean.class, new Object[] {username});
	}
	
	@Override
	public void addLoginAccount(LoginAccount loginAccount) {
		String sql="insert into LoginAccount(username, password, role, enabled) values(?, ?, ?, ?)";
		template.update(sql, loginAccount.getUsername(), loginAccount.getPassword(), loginAccount.getRole(), loginAccount.getEnabled());
	}
	
	@Override
	public boolean checkLoginCredentials(LoginAccount loginAccount) {
		String sql="select exists (select * from LoginAccount where username=? and password=?)";
		return template.queryForObject(sql, Boolean.class, new Object[] {loginAccount.getUsername(), loginAccount.getPassword()});
	}
	
	@Override
	public LoginAccount findByUsername(String username) {
		String sql="select * from LoginAccount where username=?";
		return (LoginAccount)template.queryForObject(sql, new Object[] {username}, new BeanPropertyRowMapper<>(LoginAccount.class));
	}

	@Override
	public void deleteLoginAccount(String username) {
		String sql="delete from loginAccount where username=?";
		template.update(sql, username);
	}
}
