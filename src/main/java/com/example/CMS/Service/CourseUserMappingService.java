package com.example.CMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CMS.JWTConfig.JWTAuthenticationFilter;
import com.example.CMS.Model.Course;
import com.example.CMS.Model.CourseUserMapping;
import com.example.CMS.Model.Roles;
import com.example.CMS.Model.Users;
import com.example.CMS.Repository.CourseRepository;
import com.example.CMS.Repository.CourseUserMappingRepository;
import com.example.CMS.Repository.UserRepository;

@Service
public class CourseUserMappingService {
	@Autowired
	CourseUserMappingRepository courseUserMappingRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JWTAuthenticationFilter jwtAuthenticationFilter;

	public String saveNewMapping(CourseUserMapping courseUserMapping) throws Exception {

		Optional<Users> user = userRepository.findById(courseUserMapping.getUserId());
		Optional<Course> course = courseRepository.findById(courseUserMapping.getCourseId());

		if (user.isPresent() && course.isPresent()) {

			if (!user.get().getRole().equals(Roles.STUDENT)) {
				throw new Exception("Only STUDENT REQUIRED ");
			}
			courseUserMappingRepository.save(courseUserMapping);
			return "Added New Mapping";
		} else {
			throw new Exception("Need to check UserId or CourseID");
		}
	}

	public List<CourseUserMapping> getAllCourseMapping() {
		return courseUserMappingRepository.findAll();
	}

	public List<CourseUserMapping> getAllCourseStudents(int courseId) {
		List<CourseUserMapping> allStudents = courseUserMappingRepository.findByCourseId(courseId);
		return allStudents;
	}

	public CourseUserMappingService(CourseUserMappingRepository courseUserMappingRepository) {
		this.courseUserMappingRepository = courseUserMappingRepository;

	}

}
