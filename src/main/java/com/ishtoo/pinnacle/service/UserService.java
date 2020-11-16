package com.ishtoo.pinnacle.service;

import com.ishtoo.pinnacle.models.LoginAccount;

public interface UserService {
	void save(LoginAccount loginAccount);

	LoginAccount findByUsername(String username);

	boolean doesLoginAccountExists(LoginAccount loginAccount);

	void deleteLoginAccount(String username);
}