package com.zhou.serializable;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	//瞬时态，添加了该关键字的变量是不会被序列化出去的
	private transient int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	
}
