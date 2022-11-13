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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CMS.Model.Course;
import com.example.CMS.Service.CourseManagementService;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseManagementService courseService;

	@PostMapping("/addCourse/{userId}")
	public String saveCourse(@Valid @RequestBody Course course, @PathVariable int userId) throws Exception {
		return courseService.saveNewCourse(course, userId);
	}

	@GetMapping("/getAllCourse")
	public List<Course> getAllCourse() {
		return courseService.getAllCourse();
	}

	@PutMapping("/editCourse/{id}/{userId}")
	public String updateCourse(@PathVariable int id, @Valid @RequestBody Course course, @PathVariable int userId)
			throws Exception {
		return courseService.updateCourse(id, course, userId);
	}

	@DeleteMapping("/deleteCourse/{id}/{userId}")
	public String deleteCourse(@PathVariable int id, @PathVariable int userId) throws Exception {
		return courseService.deleteCourse(id, userId);
	}

	@GetMapping("/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int courseId) {
		return courseService.findCourseById(courseId);
	}

}
