package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface SubjectRepository extends Repository<Subject, Long> {

	Page<Subject> findByCourseId(Long courseId, Pageable pageable);

	Page<Subject> findByCourseIdAndNameContainingAllIgnoringCase(Long courseId, String name, Pageable pageable);

	Subject save(Subject subject);

}
