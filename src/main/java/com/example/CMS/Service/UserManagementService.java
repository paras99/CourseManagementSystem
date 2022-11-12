package com.example.CMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CMS.Model.Users;
import com.example.CMS.Repository.UserRepository;

@Service
public class UserManagementService {

	@Autowired
	UserRepository userRepository;
	public String saveNewUser(Users user) {
		userRepository.save(user);
		return "Created new user";
	}
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
	public String updateUser(Integer id,Users user) throws Exception  {
			
		Users u = userRepository.findById(id)
		    .orElseThrow(() -> new Exception("User not found for this id :: " + id));

		    u.setEmail(user.getEmail());
		    u.setLastname(user.getLastname());
		    u.setFirstname(user.getFirstname());
		    u.setPassword(user.getPassword());
		    u.setPhone(user.getPhone());
		    u.setRole(user.getRole());
		    u.setUsername(user.getUsername());
		    
		    userRepository.save(u);
		    return "Upadted User";
	}
	
	public String deleteUser(int id) {
		userRepository.deleteById(id);
		return "Deleted the user";
	}
}
