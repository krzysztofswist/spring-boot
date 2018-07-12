package com.swist.m.krzysztof.app.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app2_user")
public class AppUser {

	private String userName;
	private String encrytedPassword;

	public AppUser() {
	}

	public AppUser(String userName, String encrytedPassword) {
		this.userName = userName;
		this.encrytedPassword = encrytedPassword;
	}

	@Id
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "encryted_password")
	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	@Override
	public String toString() {
		return this.userName + "/" + this.encrytedPassword;
	}

}
