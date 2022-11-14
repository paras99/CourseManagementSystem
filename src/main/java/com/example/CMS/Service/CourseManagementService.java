package com.example.CMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CMS.Model.Course;
import com.example.CMS.Model.Roles;
import com.example.CMS.Model.Users;
import com.example.CMS.Repository.CourseRepository;

@Service
public class CourseManagementService {

	@Autowired
	CourseRepository courseRepository;

	public String saveNewCourse(Course course,int userId)throws Exception {
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.INSTRUCTOR)) {
			throw new Exception("Only Instructor Allowed ");
		}
		courseRepository.save(course);
		return "Created new course";
	}

	public List<Course> getAllCourse(){
		return courseRepository.findAll();
	}
	
	public String updateCourse(Integer id, Course course,int userId)throws Exception {

		Course c = courseRepository.findById(id)
				.orElseThrow(() -> new Exception("course not found for this id :: " + id));

		if(c.getInstructorName().equals(course.getInstructorName())) {
		c.setTitle(course.getTitle());
		c.setInstructorName(course.getInstructorName());
		c.setCreated(course.getCreated());
		c.setModifyDate(course.getModifyDate());
//			c.setAssignment(course.getAssignment());
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.INSTRUCTOR)) {
			throw new Exception("Only Instructor Allowed ");
		}
		courseRepository.save(c);
		return "Updated course";
		}
		else
		{
			return "Sorry ! You are not Instructor of the course";
		}
	}

	public String deleteCourse(int id,int userId)throws Exception {
		Users fetched = fetchUser(userId);
		if (fetched == null)
			throw new Exception("NO DATA");

		if (!fetched.getRole().equals(Roles.INSTRUCTOR)) {
			throw new Exception("Only Instructor Allowed ");
		}
		courseRepository.deleteById(id);
		return "Deleted the course";
	}

	public Course findCourseById(int courseId) {
		Optional<Course> courseData = courseRepository.findById(courseId);
		if (courseData.isPresent()) {
			return courseData.get();
		}
		return null;
	}
	// creates the mapping by API Call and Validate the role
	public Users fetchUser(int id) {
		String url="http://localhost:8080/user/"+String.valueOf(id);
		RestTemplate restTemplate=new RestTemplate();
		Users result=restTemplate.getForObject(url, Users.class);
		return result;
	}

	public CourseManagementService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	
}
