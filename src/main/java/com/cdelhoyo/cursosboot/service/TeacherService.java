package com.cdelhoyo.cursosboot.service;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.CourseSummary;

import com.cdelhoyo.cursosboot.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
	Page<Teacher> findTeachers(String name, Pageable pageable);

	Teacher getTeacherById(Long id);

	Page<CourseSummary> getCourses(Long id, Pageable pageable);

	Page<Course> getCoursesByTeacherIdAndName(Long teacherId, String name, Pageable pageable);
}
