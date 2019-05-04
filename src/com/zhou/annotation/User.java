package com.zhou.annotation;

public class User {
	
	@ViewInject(id="1",name="小明",age=23)
	private String id;
	private String name;
	private int age;
	
	private String eat(String eat){
		System.out.println("eat:" + eat);
		return eat+"真好吃";
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
