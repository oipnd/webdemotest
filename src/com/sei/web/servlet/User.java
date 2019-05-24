package com.sei.web.servlet;

import java.util.Stack;

public class User {
	
	private String username;
	private String password;
	private String count;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Stack<Integer> stack= new Stack<>();

	public static void main(String[] args) {
		Stack<Integer> stack1= new Stack<>();
		stack1.add(1);
		stack1.push(12);
	}

}