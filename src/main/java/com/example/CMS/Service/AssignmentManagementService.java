package com.example.CMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CMS.Model.Assignment;
import com.example.CMS.Model.Roles;
import com.example.CMS.Model.Users;
import com.example.CMS.Repository.AssignmentRepository;

@Service
public class AssignmentManagementService {

	@Autowired
	AssignmentRepository assignmentRepository;

	public String saveNewAssignment(Assignment assignment, int userId) throws Exception{
		Users fetched = fetchUser(userId);

		if (!fetched.getRole().equals(Roles.INSTRUCTOR)) {
			throw new Exception("Only Instructor Allowed ");
		}
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
	public Users fetchUser(int id) {
		String url = "http://localhost:8080/user/" + String.valueOf(id);
		RestTemplate restTemplate = new RestTemplate();
		Users result = restTemplate.getForObject(url, Users.class);
		return result;
	}

	public AssignmentManagementService(AssignmentRepository assignmentRepository) {
		this.assignmentRepository = assignmentRepository;
	}
	
	
}
