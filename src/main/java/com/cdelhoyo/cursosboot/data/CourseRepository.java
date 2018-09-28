package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CourseRepository extends Repository<Course, Long> {

	Page<Course> findAll(Pageable pageable);

	Page<Course> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

	Course findById(Long id);

	Page<Course> findByTeacherId(Long teacherId,Pageable pageable);

	Page<Course> findByTeacherIdAndNameContainingAllIgnoringCase(Long teacherId, String name, Pageable pageable);

    Course save(Course course);
}
