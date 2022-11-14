package com.example.CMS.Service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CMS.Repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
class CourseManagementServiceTest {

	@Mock
	private CourseRepository courseRepository;
	
	private CourseManagementService courseManagementService;
	
	@BeforeEach
	void setUp() {
	this.courseManagementService=new CourseManagementService(this.courseRepository);	
	}
	
	@Test
	void testGetAllCourse() {
		courseManagementService.getAllCourse();
		verify(courseRepository).findAll();		
	}

	@Test
	void testGetCourseById() {
		courseManagementService.findCourseById(1);
		verify(courseRepository).findById(1);		
	}
	
}
