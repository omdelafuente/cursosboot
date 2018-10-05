package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Teacher;
import com.cdelhoyo.cursosboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@CrossOrigin
public class TeacherController {

	private TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService){
		this.teacherService = teacherService;
	}

	@GetMapping
	public Page<Teacher> findTeachers(String name, Pageable pageable) {
		return teacherService.findTeachers(name, pageable);
	}

	@GetMapping("/{id}")
	public Teacher get(@PathVariable Long id) {
		return teacherService.get(id);
	}

	@PostMapping
	public Teacher add(@RequestBody Teacher teacher) {
		return teacherService.add(teacher);
	}

	@GetMapping("/{id}/courses")
	public Page<Course> findCourses(@PathVariable Long id, String name, Pageable pageable) {
		return teacherService.findCourses(id, name, pageable);
	}

	@PostMapping("/{id}/courses")
	public Course addCourse(@PathVariable Long id, @RequestBody Course course) {
		return teacherService.addCourse(id, course);
	}

}
