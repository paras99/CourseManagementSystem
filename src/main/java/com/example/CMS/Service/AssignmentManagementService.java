package com.example.CMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CMS.Model.Assignment;
import com.example.CMS.Repository.AssignmentRepository;

@Service
public class AssignmentManagementService {

	@Autowired
	AssignmentRepository assignmentRepository;

	public String saveNewAssignment(Assignment assignment) {
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
}
