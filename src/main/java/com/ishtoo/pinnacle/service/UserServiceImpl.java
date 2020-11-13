package com.ishtoo.pinnacle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ishtoo.pinnacle.dao.LoginAccountDao;
import com.ishtoo.pinnacle.models.LoginAccount;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private LoginAccountDao loginAccountDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(LoginAccount loginAccount) {
		loginAccount.setPassword(bCryptPasswordEncoder.encode(loginAccount.getPassword()));
		loginAccountDao.addLoginAccount(loginAccount);
	}

	@Override
	public LoginAccount findByUsername(String username) {
		LoginAccount loginAccount;
		try {
			loginAccount=loginAccountDao.findByUsername(username);
		}
		catch(Exception e) {
			return null;
		}
		return loginAccount;
	}

	@Override
	public boolean doesLoginAccountExists(LoginAccount loginAccount) {
		return loginAccountDao.doesLoginAccountExists(loginAccount.getUsername());
	}

	@Override
	public void deleteLoginAccount(String username) {
		loginAccountDao.deleteLoginAccount(username);
	}


}
