package com.hibernateQuery.entity;

import java.io.Serializable;

public class IDCard implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String number;

	// 与用户的一对一关系
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
