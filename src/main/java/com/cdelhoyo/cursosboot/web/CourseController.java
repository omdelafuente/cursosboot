package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Subject;
import com.cdelhoyo.cursosboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@CrossOrigin
public class CourseController {

	private CourseService courseService;

	@Autowired
	public  CourseController(CourseService courseService){
		this.courseService = courseService;
	}

	@GetMapping
	public Page<Course> findCourses(String name, Pageable pageable) {
		return courseService.findCourses(name, pageable);
	}

	@GetMapping("/{id}")
	public Course get(@PathVariable Long id) {
		return courseService.get(id);
	}

	@GetMapping("/{id}/subjects")
	public Page<Subject> findSubjects(@PathVariable Long id, String name,  Pageable pageable) {
		return courseService.findSubjects(id, name, pageable);
	}

	@PostMapping("/{id}/subjects")
	public Subject addSubject(@PathVariable Long id, @RequestBody Subject subject) {
		return courseService.addSubject(id, subject);
	}


}
