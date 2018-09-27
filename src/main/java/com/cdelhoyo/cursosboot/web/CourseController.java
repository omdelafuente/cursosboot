package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Subject;
import com.cdelhoyo.cursosboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

	private CourseService courseService;

	@Autowired
	public  CourseController(CourseService courseService){
		this.courseService = courseService;
	}

	@GetMapping
	@Transactional(readOnly = true)
	public Page<Course> getAll(Pageable pageable) {
		return courseService.findCourses(null, pageable);
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Course get(@PathVariable Long id) {
		return courseService.getCourseById(id);
	}

	@GetMapping("/name/{name}")
	@Transactional(readOnly = true)
	public Page<Course> getAllByName(@PathVariable String name, Pageable pageable) {
		return courseService.findCourses(name, pageable);
	}

	@GetMapping("/{id}/subject")
	@Transactional(readOnly = true)
	public Page<Subject> getSubjects(@PathVariable Long id, Pageable pageable) {
		return courseService.getSubjects(id, pageable);
	}

	@PutMapping("/{id}/subject/{name}")
	@Transactional
	public Subject addSubject(@PathVariable Long id, @PathVariable String name) {
		return courseService.addSubject(id, name);
	}


}
