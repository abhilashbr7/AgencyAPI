package com.qa.data;

public class Individuo_Certificate {
	
	String grant_type;
	String username;
	String password;
	String client_id;
	String client_secret;
	
	public Individuo_Certificate() {
		
	}
	
	public Individuo_Certificate(String grant_type, String username,String password,String client_id,String client_secret)
	{
		this.grant_type = grant_type;
		this.username = username;
		this.password = password;
		this.client_id = client_id;
		this.client_secret = client_secret;
	}

	
	public String getgrant_type() {
		return grant_type;
	}

	public void setgrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	public String getclient_id() {
		return client_id;
	}

	public void setclient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getclient_secret() {
		return client_secret;
	}

	public void setclient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	
	


}