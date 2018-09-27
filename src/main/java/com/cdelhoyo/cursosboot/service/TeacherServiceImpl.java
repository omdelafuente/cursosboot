package com.cdelhoyo.cursosboot.service;

import com.cdelhoyo.cursosboot.data.CourseRepository;
import com.cdelhoyo.cursosboot.data.TeacherRepository;
import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.CourseSummary;

import com.cdelhoyo.cursosboot.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component("teacherService")
@Transactional
class TeacherServiceImpl implements TeacherService {

	private final TeacherRepository teacherRepository;

	private final CourseRepository courseRepository;

	TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
		this.teacherRepository = teacherRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public Page<Teacher> findTeachers(String name, Pageable pageable) {
		Page<Teacher> teachers;
		if (StringUtils.hasLength(name)) {
			teachers = teacherRepository.findByNameContainingAllIgnoringCase(name.trim(), pageable);
		}else{
			teachers =  teacherRepository.findAll(pageable);
		}
		return teachers;
	}


	@Override
	public Teacher getTeacherById(Long id) {
		Assert.notNull(id, "Id must not be null");
		return teacherRepository.findById(id);
	}

	@Override
	public Page<CourseSummary> getCourses(Long id, Pageable pageable) {
		Assert.notNull(id, "Id must not be null");
		return courseRepository.findByTeacherId(id, pageable);
	}

	@Override
	public Page<Course> getCoursesByTeacherIdAndName(Long teacherId, String name, Pageable pageable) {
		Assert.notNull(teacherId, "IdTeacher must not be null");
		Assert.hasLength(name, "Name must not be empty");
		return courseRepository.findByTeacherIdAndNameContainingAllIgnoringCase(teacherId, name, pageable);
	}

}
