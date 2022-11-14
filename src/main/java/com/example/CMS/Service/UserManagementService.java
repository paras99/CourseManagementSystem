package com.example.CMS.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CMS.JWTConfig.JWTAuthenticationFilter;
import com.example.CMS.Model.Roles;
import com.example.CMS.Model.Users;
import com.example.CMS.Repository.UserRepository;

@Service
public class UserManagementService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JWTAuthenticationFilter jwtAuthenticationFilter;

	public String saveNewUser(Users user) {
		//Here Encoding the Password by BASE64 to protect
		String encodedPassword = Base64.getUrlEncoder().encodeToString(user.getPassword().getBytes());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return "Created new user";
	}

	public List<Users> getAllUsers(int userId) throws Exception {
		boolean fetched = fetchUser(userId);
		if(fetched==true)
		return userRepository.findAll();
		else
			throw new Exception("ONLY ADMIN CAN FETCH");
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
		
		boolean fetched = fetchUser(userId);
		if(fetched==true) {
		userRepository.save(u);
		return "Updated User";
		}
		else {
			throw new Exception("ONLY ADMIN CAN UPDATE");
		}
	}

	public String deleteUser(int id,int userId) throws Exception {
		boolean fetched = fetchUser(userId);
		if(fetched==true) {
		userRepository.deleteById(id);
		return "Deleted the user";
		}
		else {
			throw new Exception("ONLY ADMIN CAN DELETE");
		}
	}

	public Users findUserById(int userId) {
		Optional<Users> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			return userData.get();
		}
		return null;
	}
	// creates the mapping by API Call and Validate the role
	public boolean fetchUser(int userId) {
		HttpServletRequest request=jwtAuthenticationFilter.getHeader();
		String requestTokenHeader=request.getHeader("Authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", requestTokenHeader);
		String url = "http://localhost:8080/user/" + String.valueOf(userId);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = new HttpEntity<>("some body", headers);
		ResponseEntity<String> response =restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		System.out.println(response.getBody().contains("ADMIN"));
		return response.getBody().contains("ADMIN"); 
	}

	public List<Users> findAllStudents(int userId) throws Exception{
		boolean fetched = fetchUser(userId);
		if(fetched==true) {
		List<Users> check= userRepository.findAll();
		List<Users> allStudent = check.stream()
				  .filter(student -> Roles.STUDENT.equals(student.getRole())).collect(Collectors.toList());
			return allStudent;	
		}
		else
			return null;
	}

	public UserManagementService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
