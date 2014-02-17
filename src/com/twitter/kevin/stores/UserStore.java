package com.twitter.kevin.stores;

public class UserStore {
	int user_ID;
	String username;
	String password;
	String email;

	public int getID() {
		return user_ID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setID(int ID) {
		this.user_ID = ID;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public void setPassword(String name) {
		this.password = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}