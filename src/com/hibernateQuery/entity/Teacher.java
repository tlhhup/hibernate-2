package com.hibernateQuery.entity;

import java.io.Serializable;
import java.util.Set;

public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String address;

	//与学生的一对多的关系
	private Set<Student> students;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
