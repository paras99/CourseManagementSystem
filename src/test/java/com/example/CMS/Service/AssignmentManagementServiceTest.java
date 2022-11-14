package com.example.CMS.Service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CMS.Repository.AssignmentRepository;

@ExtendWith(MockitoExtension.class)
class AssignmentManagementServiceTest {

	@Mock
	private AssignmentRepository assignmentRepository;
	
	private AssignmentManagementService assignmentManagementService;
	
	@BeforeEach
	void setUp() {
		this.assignmentManagementService=new AssignmentManagementService(this.assignmentRepository);
	}
	
	
	@Test
	void testGetAllAssignment() {
		assignmentManagementService.getAllAssignment();
		verify(assignmentRepository).findAll();
	}

}
