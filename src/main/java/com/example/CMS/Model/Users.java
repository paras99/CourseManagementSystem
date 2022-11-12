package com.example.CMS.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*JSON DATA 
{
"username":"Rahul",
"password":"abc",
"firstname":"Rahul",
"lastname":"Bajaj",
"role":"INSTRUCTOR",
"phone":"998274352",
"email":"Bajaj"
}

*/


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	public enum Role {
		ADMIN, EDITOR, USER;
	}

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	@NotNull
	private Roles role;
	private String phone;

	private String email;

	// No setter for ID
	public int getId() {
		return id;
	}

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", role=" + role + ", phone=" + phone + ", email=" + email + "]";
	}

}
