package com.ishtoo.pinnacle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userDetails instanceof UserDetails) {
			return ((UserDetails)userDetails).getUsername();
		}
			
		return null;
	}

	@Override
	public void autoLogin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
        		new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        
        try {
        	authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        	if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        catch(Exception e){
        	System.out.println(e);
        }
	}

	@Override
	public void autoLogout() {
		SecurityContextHolder.clearContext();
		
	}
}
