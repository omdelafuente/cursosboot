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
@RequestMapping("/courses")
public class CourseController {

	private CourseService courseService;

	@Autowired
	public  CourseController(CourseService courseService){
		this.courseService = courseService;
	}

	@GetMapping
	@Transactional(readOnly = true)
	public Page<Course> findCourses(String name, Pageable pageable) {
		return courseService.findCourses(name, pageable);
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Course get(@PathVariable Long id) {
		return courseService.get(id);
	}

	@GetMapping("/{id}/subjects")
	@Transactional(readOnly = true)
	public Page<Subject> findSubjects(@PathVariable Long id, String name,  Pageable pageable) {
		return courseService.findSubjects(id, name, pageable);
	}

	@PostMapping("/{id}/subjects")
	@Transactional
	public Subject addSubject(@PathVariable Long id, @RequestBody String name) {
		return courseService.addSubject(id, name);
	}


}
