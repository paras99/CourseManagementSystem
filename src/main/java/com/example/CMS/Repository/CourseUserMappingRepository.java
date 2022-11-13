package com.example.CMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CMS.Model.CourseUserMapping;

public interface CourseUserMappingRepository extends JpaRepository <CourseUserMapping, Integer> {

	public List<CourseUserMapping> findByCourseId(int courseId);
}
