package com.hibernateQuery.entity;

import java.io.Serializable;

public class IDCard implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String number;

	// ���û���һ��һ��ϵ
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
