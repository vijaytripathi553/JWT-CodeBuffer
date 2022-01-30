package com.dailycodebuffer.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.jwt.dto.StudentDTO;
import com.dailycodebuffer.jwt.entity.Student;
import com.dailycodebuffer.jwt.service.UserService;
import com.dailycodebuffer.jwt.utility.JWTUtility;

@RestController
public class HomeController {
	
	@Autowired
	private JWTUtility jWTUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;  // so far we have not created AuthenticationManager bean in SpringConfigration.java. So first go and create it
	
	
	@Autowired
	private UserService userService;
	
	
	
	// STEP 7: here whenever we are trying to access this application this message should be display to the user but even before displaying this message to the user our 
	//request should be authenticated sp spring security will come in picture nd it will ask for the credential
	@GetMapping("/")
	public String home() 
	{
		return " Welcome to Daily Code Buffer .!!";
	}
	
	@PostMapping("/authenticate")
	public StudentDTO authenticate(@RequestBody Student student)throws Exception
	{
		// Now here i have to authenticate my username and password with the Authentication Manager of Spring Security and if its valid then i have to create jwt token and give return back
		//  For that will be using jwt utility and Authentication Manageer from the Spring security. So first lets annotate it at the top
		
		try
		{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							student.getUsername(), student.getPassword()
															)
												);

		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Invalid_Credential"+e);
			
		}
		
		// Now once the authentication is done let's create the JWT Token
		// So first will create the USer Details object from the user name that we have
		
		// So will create the final UserDetails from the spring security
		
		final UserDetails userDetails                        // UserDetails from spring security
				=userService.loadUserByUsername(student.getUsername());
		
		final String token=
				jWTUtility.generateToken(userDetails);
		
		// Once the token is created we need to wrap this token into response and send back
		
		return new StudentDTO(token);
		
		
		
	}

}
