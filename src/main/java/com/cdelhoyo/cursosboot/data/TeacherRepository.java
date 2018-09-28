package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface TeacherRepository extends Repository<Teacher, Long> {

	Page<Teacher> findAll(Pageable pageable);

	Page<Teacher> findByNameContainingAllIgnoringCase(String name, Pageable pageable);

	Teacher findById(Long id);

	Teacher save(Teacher teacher);
}
