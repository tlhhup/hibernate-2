package com.hibernateQuery.entity;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String address;
	private int age;

	// 构建与教师的多对一关系：外键
	private Teacher teacher;

	private int count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + ", count=" + count
				+ "]";
	}

}
