package com.example.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CMS.Model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
