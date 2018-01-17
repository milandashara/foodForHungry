package com.foodForHungry.bo;

/**
 * Created by mashara on 6/12/17.
 */
public class UserBO {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String verifyPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserBO{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
