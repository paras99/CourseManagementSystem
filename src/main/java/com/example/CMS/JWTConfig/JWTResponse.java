package com.example.CMS.JWTConfig;

public class JWTResponse {

	String token;
	
	public JWTResponse(){
		
	}

	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
