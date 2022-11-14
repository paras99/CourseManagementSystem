package com.example.CMS.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CMS.Model.Roles;
import com.example.CMS.Model.Users;
import com.example.CMS.Repository.UserRepository;

@Service
public class UserManagementService {

	@Autowired
	UserRepository userRepository;

	public String saveNewUser(Users user) {
		//Here Encoding the Password by BASE64 to protect
		String encodedPassword = Base64.getUrlEncoder().encodeToString(user.getPassword().getBytes());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return "Created new user";
	}

	public List<Users> getAllUsers(int userId) throws Exception {
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.ADMIN)) {  //checking role based acccess
			throw new Exception("Only ADMIN Allowed ");
		}
		return userRepository.findAll();
	}

	public String updateUser(int id, Users user, int userId) throws Exception {

		Users u = userRepository.findById(id).orElseThrow(() -> new Exception("User not found for this id :: " + id));

		u.setEmail(user.getEmail());
		u.setLastname(user.getLastname());
		u.setFirstname(user.getFirstname());
		String encodedPassword = Base64.getUrlEncoder().encodeToString(user.getPassword().getBytes());
		user.setPassword(encodedPassword);
		u.setPassword(user.getPassword());
		u.setPhone(user.getPhone());
		u.setRole(user.getRole());
		u.setUsername(user.getUsername());
		
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.ADMIN)) {
			throw new Exception("Only ADMIN Allowed ");
		}
		userRepository.save(u);
		return "Updated User";
	}

	public String deleteUser(int id,int userId) throws Exception {
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.ADMIN)) {
			throw new Exception("Only ADMIN Allowed ");
		}
		userRepository.deleteById(id);
		return "Deleted the user";
	}

	public Users findUserById(int userId) {
		Optional<Users> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			return userData.get();
		}
		return null;
	}
	// creates the mapping by API Call and Validate the role
	public Users fetchUser(int userId) {
		String url = "http://localhost:8080/user/" + String.valueOf(userId);
		RestTemplate restTemplate = new RestTemplate();
		Users result = restTemplate.getForObject(url, Users.class);
		return result;
	}

	public List<Users> findAllStudents(int userId) throws Exception{
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.ADMIN)) {
			throw new Exception("Only ADMIN Allowed ");
		}
		List<Users> check= userRepository.findAll();
		List<Users> allStudent = check.stream()
				  .filter(student -> Roles.STUDENT.equals(student.getRole())).collect(Collectors.toList());
			return allStudent;	  
	}

	public UserManagementService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
