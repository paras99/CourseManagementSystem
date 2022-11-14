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
import com.example.CMS.Model.Course;
import com.example.CMS.Repository.CourseRepository;

@Service
public class CourseManagementService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	JWTAuthenticationFilter jwtAuthenticationFilter;

	public String saveNewCourse(Course course,int userId)throws Exception {
		boolean fetched = fetchUser(userId);
		if(fetched==true) {
		courseRepository.save(course);
		return "Created new course";
		}
		else {
			throw new Exception("Only Instructor is allowed to create course");
		}
	}

	public List<Course> getAllCourse(){
		return courseRepository.findAll();
	}
	
	public String updateCourse(Integer id, Course course,int userId)throws Exception {
		boolean fetched = fetchUser(userId);
		if(fetched==true) {
		Course c = courseRepository.findById(id)
				.orElseThrow(() -> new Exception("course not found for this id :: " + id));

		if(c.getInstructorName().equals(course.getInstructorName())) {
		c.setTitle(course.getTitle());
		c.setInstructorName(course.getInstructorName());
		c.setCreated(course.getCreated());
		c.setModifyDate(course.getModifyDate());}
		else {
			throw new Exception("Not Same Instructor");
		}
//			c.setAssignment(course.getAssignment());
		
		courseRepository.save(c);
		return "Updated course";
		}
		
		else
		{
			throw new Exception("Sorry ! You are not Instructor of the course");
		}
	}

	public String deleteCourse(int id,int userId)throws Exception {
		boolean fetched = fetchUser(userId);
		if(fetched==true) {
		courseRepository.deleteById(id);
		return "Deleted the course";}
		else {
			throw new Exception("Only Instructor has Authority");
		}
	}

	public Course findCourseById(int courseId) {
		Optional<Course> courseData = courseRepository.findById(courseId);
		if (courseData.isPresent()) {
			return courseData.get();
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

	public CourseManagementService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	
}
