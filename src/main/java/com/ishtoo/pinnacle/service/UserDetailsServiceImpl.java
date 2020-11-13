package com.ishtoo.pinnacle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ishtoo.pinnacle.models.LoginAccount;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginAccount loginAccount = userService.findByUsername(username);
        if (loginAccount == null) {
            throw new UsernameNotFoundException("Invalid Username");
        }
        List<GrantedAuthority> grantList = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority(loginAccount.getRole());
        grantList.add(authority);
        return new org.springframework.security.core.userdetails.User(loginAccount.getUsername(), loginAccount.getPassword(), grantList);
    
	}		
	

}