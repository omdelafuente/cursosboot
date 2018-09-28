package com.cdelhoyo.cursosboot.service;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

	Page<Course> findCourses(String name, Pageable pageable);

	Course get(Long id);

	Page<Subject> findSubjects(Long courseId, String name, Pageable pageable);

	Subject addSubject(Long courseId, Subject subject);

}
