package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.CourseSummary;
import com.cdelhoyo.cursosboot.domain.Teacher;
import com.cdelhoyo.cursosboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService){
		this.teacherService = teacherService;
	}

	@GetMapping()
	@Transactional(readOnly = true)
	public Page<Teacher> getAll(Pageable pageable) {
		return teacherService.findTeachers(null, pageable);
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Teacher get(@PathVariable Long id) {
		return teacherService.getTeacherById(id);
	}

	@GetMapping("/name/{name}")
	@Transactional(readOnly = true)
	public Page<Teacher> getAllByName(@PathVariable String name, Pageable pageable) {
		return teacherService.findTeachers(name, pageable);
	}

	@GetMapping("/{id}/course")
	@Transactional(readOnly = true)
	public Page<CourseSummary> getCourses(@PathVariable Long id, Pageable pageable) {
		return teacherService.getCourses(id, pageable);
	}

	@GetMapping("/{teacherId}/course/name/{name}")
	@Transactional(readOnly = true)
	public Page<Course>  getCoursesByName(@PathVariable Long teacherId, @PathVariable String name, Pageable pageable) {
		return teacherService.getCoursesByTeacherIdAndName(teacherId, name, pageable);
	}

}
