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

import com.example.CMS.Model.Course;
import com.example.CMS.Service.CourseManagementService;

@RestController
public class CourseController {
	@Autowired
	CourseManagementService courseService;
	
	@PostMapping("/addCourse")
	public String saveCourse(@Valid @RequestBody Course course) {
		return courseService.saveNewCourse(course);
	}

	@GetMapping("/getAllCourse")
	public List<Course> getAllCourse(){
		return courseService.getAllCourse();
	}
	
	@PutMapping("/editCourse/{id}")
	public String updateCourse(@PathVariable int id,@Valid @RequestBody Course course)throws Exception {
		return courseService.updateCourse(id,course);
	}
	
	@DeleteMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable int id)throws Exception {
		return courseService.deleteCourse(id);
	}
	
	@GetMapping("/Course/{CourseId}")
	public Course findCourseById(@PathVariable("courseId") int courseId) {
		return courseService.findCourseById(courseId);
	}
	
}
