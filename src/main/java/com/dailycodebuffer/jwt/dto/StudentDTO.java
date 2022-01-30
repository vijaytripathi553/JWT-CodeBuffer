package com.dailycodebuffer.jwt.dto;

public class StudentDTO {
	// Private Data members
	private String jwtToken;
	
	
	
	// Getters and Setters
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public StudentDTO(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
	
	
	// Construactor
	
	
	
	
	

}
