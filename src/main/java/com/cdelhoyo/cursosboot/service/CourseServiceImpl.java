package com.cdelhoyo.cursosboot.service;

import com.cdelhoyo.cursosboot.data.CourseRepository;
import com.cdelhoyo.cursosboot.data.SubjectRepository;
import com.cdelhoyo.cursosboot.domain.Course;

import com.cdelhoyo.cursosboot.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component("courseService")
@Transactional
class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	private final SubjectRepository subjectRepository;

	CourseServiceImpl(CourseRepository courseRepository, SubjectRepository subjectRepository) {
		this.courseRepository = courseRepository;
		this.subjectRepository = subjectRepository;
	}


	@Override
	public Page<Course> findCourses(String name, Pageable pageable) {
		Page<Course> courses;
		if (StringUtils.hasLength(name)) {
			courses = this.courseRepository.findByNameContainingAllIgnoringCase(name.trim(), pageable);
		}else{
			courses =  this.courseRepository.findAll(pageable);
		}
		return courses;
	}

	@Override
	public Course getCourseById(Long id) {
		Assert.notNull(id, "Id must not be null");
		return this.courseRepository.findById(id);
	}

	@Override
	public Page<Subject> getSubjects(Long courseId, Pageable pageable) {
		Assert.notNull(courseId, "Course must not be null");
		return this.subjectRepository.findByCourseId(courseId, pageable);
	}

	@Override
	public Subject addSubject(Long courseId, String name) {
		Assert.notNull(courseId, "Course must not be null");
		return this.subjectRepository.save(new Subject(name, getCourseById(courseId)));
	}

}
