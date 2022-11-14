package com.example.CMS.Service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CMS.Repository.CourseUserMappingRepository;


@ExtendWith(MockitoExtension.class)
class CourseUserMappingServiceTest {

	@Mock
	private CourseUserMappingRepository courseUserMappingRepository;
		
	private CourseUserMappingService courseUserMappingService;
	
	@BeforeEach
	void setUp() {
		this.courseUserMappingService=new CourseUserMappingService(this.courseUserMappingRepository);
	}
	
	@Test
	void testGetAllCourseMapping() {
		courseUserMappingService.getAllCourseMapping();
		verify(courseUserMappingRepository).findAll();
	}

}
