package com.ishtoo.pinnacle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ishtoo.pinnacle.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityServiceConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Bean
    BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
	            .antMatchers("/admin/**").hasRole("admin")
	            .antMatchers("/student/**").hasAnyRole("student", "teacher", "admin")
	            .antMatchers("/teacher/profile/**").hasAnyRole("student", "teacher", "admin")
	            .antMatchers("/teacher/**").hasAnyRole("teacher", "admin")
	            .antMatchers("/logout/**").hasAnyRole("student","teacher","admin")
	            .antMatchers("/batch/**").hasAnyRole("student","teacher","admin")
	            .antMatchers("/test/**").hasAnyRole("student","teacher","admin")
	            .antMatchers("/**").permitAll()
            .and()
            .formLogin()
            	.loginPage("/login")
            	.failureUrl("/login-error")
            .and()
            	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");          
    }

}