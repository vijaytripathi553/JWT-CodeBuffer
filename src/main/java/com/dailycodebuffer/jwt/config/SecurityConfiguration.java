package com.dailycodebuffer.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dailycodebuffer.jwt.filter.JwtFilter;
import com.dailycodebuffer.jwt.service.UserService;

// STEP 1: Do extends the Web SecurityConfigurerAdapter and annotate the class with @EnableWebSecurity
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	// STEP 6
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtFilter jwtFilter;

	
	// STEP 2: Then do override the configure method for WebSecurityConfigurerAdapter
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// so will be implementing custom userDetailsService. Spring SSEcurity provides the userDetailsService and we need to use our users stored in our database. 
		//So generally what will be doing is will be getting user from the database and for that user will becreating the custom user object.
		//So here what we have to do is we have to create userDetailsService and that userDetailsService will get the user detail from the database and send that user information
		// So here we are not implementing te database layer. Will be just implementing the service layer and i will just create the dummy user and will send it
		
		
		// Let me crate the user service over here so that i can use that user service at line no 28. So i went to the service package and created one class called UserService and annoted it with @Service
		auth.userDetailsService(userService);
	}
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception 
	{
		return super.authenticationManager();
	}
	
	
	// This method has been overriden to authorize the request
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()				// disable the cross reference
		.authorizeRequests()
		.antMatchers("/authenticate")
		.permitAll()
		.anyRequest()		// Any Request
		.authenticated()	//  should be authenticated
		.and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	
	

}
