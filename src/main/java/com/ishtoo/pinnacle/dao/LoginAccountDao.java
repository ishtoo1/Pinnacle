package com.ishtoo.pinnacle.dao;

import com.ishtoo.pinnacle.models.LoginAccount;

public interface LoginAccountDao {

	boolean doesLoginAccountExists(String username);

	void addLoginAccount(LoginAccount loginAccount);

	boolean checkLoginCredentials(LoginAccount loginAccount);

	LoginAccount findByUsername(String username);

	void deleteLoginAccount(String username);

}
