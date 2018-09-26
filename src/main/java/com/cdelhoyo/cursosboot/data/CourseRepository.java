package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.CourseSummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface CourseRepository extends Repository<Course, Long> {

	Page<Course> findAll(Pageable pageable);

	Page<Course> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

	Course findById(Long id);

	Page<Course> findByTeacherIdAndNameContainingAllIgnoringCase(Long teacherId, String name, Pageable pageable);

	@Query("select c.teacher as teacher, c.name as name, count(s) as numberOfSubjects "
			+ "from Course c left outer join c.subjects s where c.teacher.id = ?1 group by c")
	Page<CourseSummary> findByTeacherId(Long id, Pageable pageable);

}
