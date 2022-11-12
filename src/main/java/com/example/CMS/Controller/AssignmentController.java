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

import com.example.CMS.Model.Assignment;
import com.example.CMS.Service.AssignmentManagementService;

@RestController
public class AssignmentController {
	@Autowired
	AssignmentManagementService assignmentService;
	
	@PostMapping("/addAssignment")
	public String saveAssignment(@Valid @RequestBody Assignment assignment) {
		return assignmentService.saveNewAssignment(assignment);
	}

	@GetMapping("/getAllAssignment")
	public List<Assignment> getAllAssignment(){
		return assignmentService.getAllAssignment();
	}
	
	@PutMapping("/editAssignment/{id}")
	public String updateAssignment(@PathVariable int id,@Valid @RequestBody Assignment assignment)throws Exception {
		return assignmentService.updateAssignment(id,assignment);
	}
	
	@DeleteMapping("/deleteAssignment/{id}")
	public String deleteAssignment(@PathVariable int id)throws Exception {
		return assignmentService.deleteAssignment(id);
	}
	
	@GetMapping("/Assignment/{AssignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		return assignmentService.findAssignmentById(assignmentId);
	}
	
}
