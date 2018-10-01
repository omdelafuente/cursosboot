package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Rollback
public class TeacherRepositoryIT {

	@Autowired
	TeacherRepository sut;

	@Test
	public void findAllShouldReturnAllTeachers() {
		Page<Teacher> teachers = sut.findAll(PageRequest.of(0, 3, Sort.Direction.ASC, "name"));

		Assert.assertThat(teachers.getTotalElements(),equalTo(2L));
		assertTrue(teachers.getContent().stream().anyMatch(teacher -> teacher.equals(new Teacher(1L,"Roberto Canales"))));
		assertTrue(teachers.getContent().stream().anyMatch(teacher -> teacher.equals(new Teacher(2L,"Rubén Aguilera Díaz-Heredero"))));
	}

	@Test
	public void findByNameContainingAllIgnoringCaseFindingByRobertoShouldReturnTOneTeachers() {
		Page<Teacher> teachers = sut.findByNameContainingAllIgnoringCase("roberto", PageRequest.of(0, 3, Sort.Direction.ASC, "name"));

		Assert.assertThat(teachers.getTotalElements(),equalTo(1L));
		assertTrue(teachers.getContent().stream().anyMatch(teacher -> teacher.equals(new Teacher(1L,"Roberto Canales"))));
	}

	@Test
	public void findById1ShouldReturnRoberto() {
		Teacher teacher = sut.findById(1L);

		Assert.assertThat(teacher,equalTo(new Teacher(1L,"Roberto Canales")));
	}

	@Test
	@Transactional
	public void saveShouldSaveTestTeacher() {
		Teacher teacherToSave = new Teacher("test");

		sut.save(teacherToSave);

		Page<Teacher> teachers = sut.findAll(PageRequest.of(0, 100, Sort.Direction.ASC, "name"));
		assertTrue(teachers.getContent().stream().anyMatch(teacher -> teacher.equals(teacherToSave)));
	}

}
