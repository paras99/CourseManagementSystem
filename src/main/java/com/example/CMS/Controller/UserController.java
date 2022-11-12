package com.example.CMS.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CMS.Model.Users;
import com.example.CMS.Service.UserManagementService;

@RestController
public class UserController {
	@Autowired
	UserManagementService userService;
	
	@PostMapping("/addUser")
	public String saveUser(@Valid @RequestBody Users user) {
		return userService.saveNewUser(user);
	}

	@GetMapping("/getAll")
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PutMapping("/editUser/{id}")
	public String updateUser(@PathVariable int id,@Valid @RequestBody Users user)throws Exception {
		return userService.updateUser(id,user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id)throws Exception {
		return userService.deleteUser(id);
	}
}
