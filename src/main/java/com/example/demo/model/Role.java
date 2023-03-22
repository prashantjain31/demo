package com.example.demo.model;

public enum Role {
	INTERN("Intern"), MANAGER("Manager"), LEADER("Leader"), TESTER("Tester");
	
	private String role;
	
	private Role(String role) {
		this.role = role;
	}
	public String getRole() {
		return this.role;
	}
}
