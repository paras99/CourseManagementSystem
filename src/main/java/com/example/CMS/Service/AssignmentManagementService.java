package com.example.CMS.Service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CMS.JWTConfig.JWTAuthenticationFilter;
import com.example.CMS.Model.Assignment;
import com.example.CMS.Repository.AssignmentRepository;

@Service
public class AssignmentManagementService {

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	JWTAuthenticationFilter jwtAuthenticationFilter;

	public String saveNewAssignment(Assignment assignment, int userId) throws Exception{

		boolean fetched = fetchUser(userId);
		if(fetched==true)
		assignmentRepository.save(assignment);
		return "Created new assignment";
	}

	public List<Assignment> getAllAssignment() {
		return assignmentRepository.findAll();
	}

	public String updateAssignment(Integer id, Assignment assignment) throws Exception {

		Assignment c = assignmentRepository.findById(id)
				.orElseThrow(() -> new Exception("assignment not found for this id :: " + id));

		c.setTitle(assignment.getTitle());
		c.setInstructorName(assignment.getInstructorName());
		c.setCreated(assignment.getCreated());
		c.setModifyDate(assignment.getModifyDate());

		assignmentRepository.save(c);
		return "Updated assignment";
	}

	public String deleteAssignment(int id) {
		assignmentRepository.deleteById(id);
		return "Deleted the assignment";
	}

	public Assignment findAssignmentById(int assignmentId) {
		Optional<Assignment> assignmentData = assignmentRepository.findById(assignmentId);
		if (assignmentData.isPresent()) {
			return assignmentData.get();
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
		System.out.println(response.getBody().contains("INSTRUCTOR"));
		return response.getBody().contains("INSTRUCTOR"); 
	}

	public AssignmentManagementService(AssignmentRepository assignmentRepository) {
		this.assignmentRepository = assignmentRepository;
	}
	
	
}
