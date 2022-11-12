package com.example.CMS.Model;

public enum Roles {
	ADMIN("ADMIN"),

	STUDENT("STUDENT"),

	INSTRUCTOR("INSTRUCTOR");

	private String role;

	private Roles(String role) {

		this.role = role;
	}

	public String getrole() {

		return this.role;
	}
}
