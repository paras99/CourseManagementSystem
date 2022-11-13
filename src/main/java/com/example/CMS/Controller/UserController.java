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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CMS.Model.Users;
import com.example.CMS.Service.UserManagementService;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserManagementService userService;
	
	@PostMapping("/addUser")
	public String saveUser(@Valid @RequestBody Users user) {
		return userService.saveNewUser(user);
	}

	@GetMapping("/getAllUser/{userId}")
	public List<Users> getAllUsers(@PathVariable("userId") int userId)throws Exception{
		return userService.getAllUsers(userId);
	}
	
	@PutMapping("/editUser/{id}/{userId}")
	public String updateUser(@PathVariable int id,@Valid @RequestBody Users user,@PathVariable int userId)throws Exception {
		return userService.updateUser(id,user,userId);
	}
	
	@DeleteMapping("/deleteUser/{id}/{userId}")
	public String deleteUser(@PathVariable int id,@PathVariable int userId)throws Exception {
		return userService.deleteUser(id,userId);
	}
	
	@GetMapping("/{userId}")
	public Users findUserById(@PathVariable("userId") int userId) {
		return userService.findUserById(userId);
	}
	
	@GetMapping("/findAllStudents/{userId}")
	public List<Users> findAllStudents(@PathVariable("userId") int userId) throws Exception{
		return userService.findAllStudents(userId);
	}
	
}
