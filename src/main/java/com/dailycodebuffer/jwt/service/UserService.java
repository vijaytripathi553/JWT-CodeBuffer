package com.dailycodebuffer.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService  //STEP 3: UserDetailsService is an interface from spring Security. We are overriding the method of UserDetailsService interface here to load the user from db based on usernanme 
{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//STEP 4: Writing logic to get the user from the database based on the usernae we are passing here
		
		return new User("admin", "admin123", new ArrayList<>()); // username, password and Role
		//STEP 5: We are going to use this service in our SecurityConfiguration.java file. So lets move to the SecurityConfiguration.java file
	}
}
