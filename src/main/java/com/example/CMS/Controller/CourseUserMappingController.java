package com.example.CMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CMS.Model.CourseUserMapping;
import com.example.CMS.Service.CourseUserMappingService;

@RestController
@RequestMapping("/mapping")
public class CourseUserMappingController {
	@Autowired
	CourseUserMappingService courseService;
	
	@PostMapping("/addMapping")
	public String saveCourse(@RequestBody CourseUserMapping courseUserMapping)throws Exception  {
		return courseService.saveNewMapping(courseUserMapping);
	}
	
	@GetMapping("/getAllCourse")
	public List<CourseUserMapping> getAllCourse(){
	return courseService.getAllCourseMapping();
	}
	
	@GetMapping("/getStudents/{courseId}")
	public List<CourseUserMapping> getStudents(@PathVariable int courseId){
	return courseService.getAllCourseStudents(courseId);
	}
}
