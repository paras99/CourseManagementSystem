package com.example.CMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CMS.Model.Course;
import com.example.CMS.Repository.CourseRepository;

@Service
public class CourseManagementService {

	@Autowired
	CourseRepository courseRepository;
	public String saveNewCourse(Course course) {
		courseRepository.save(course);
		return "Created new course";
	}
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}
	public String updateCourse(Integer id,Course course) throws Exception  {
			
		Course c = courseRepository.findById(id)
		    .orElseThrow(() -> new Exception("course not found for this id :: " + id));

			c.setTitle(course.getTitle());
			c.setInstructorName(course.getInstructorName());
			c.setCreated(course.getCreated());
			c.setModifyDate(course.getModifyDate());
//			c.setAssignment(course.getAssignment());
		    
		    courseRepository.save(c);
		    return "Updated course";
	}
	
	public String deleteCourse(int id) {
		courseRepository.deleteById(id);
		return "Deleted the course";
	}
	
	public Course findCourseById(int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
}
