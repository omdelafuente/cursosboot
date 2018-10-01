package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.cdelhoyo.cursosboot.domain.Level.ELEMENTARY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Rollback
public class CourseRepositoryIT {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository sut;


    @Test
    public void findAllShouldReturn5CoursesContentTheirs() {
        Teacher teacher = teacherRepository.findById(2L);

        Page<Course> courses = sut.findAll(PageRequest.of(0, 3, Direction.ASC, "name"));

        assertThat(courses.getTotalElements(), equalTo(5L));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.equals(new Course(3L, "Instalación de GitLab con HTTPS", true, ELEMENTARY, teacher))));
    }

    @Test
    public void findByNameContainingAllIgnoringCaseFindingByGitLabShouldReturnTwoCoursesContentTheirs() {
        Teacher teacher = teacherRepository.findById(2L);

        Page<Course> courses = sut.findByNameContainingAllIgnoringCase("gitlab", PageRequest.of(0, 3, Direction.ASC, "name"));

        assertThat(courses.getTotalElements(), equalTo(2L));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.equals(new Course(3L, "Instalación de GitLab con HTTPS", true, ELEMENTARY, teacher))));
    }

    @Test
    public void findById3ShouldReturnCourseOfGitLab() {
        Teacher teacher = teacherRepository.findById(2L);

        Course course = sut.findById(3L);

        assertThat(course, equalTo(new Course(3L, "Instalación de GitLab con HTTPS", true, ELEMENTARY, teacher)));
    }

    @Test
    public void findByTeacherId2ShouldReturnFourCoursesAndContentTheirs() {
        Teacher teacher = teacherRepository.findById(2L);

        Page<Course> courses = sut.findByTeacherId(2L, PageRequest.of(0, 10, Direction.ASC, "name"));

        assertThat(courses.getTotalElements(), equalTo(4L));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.equals(new Course(3L, "Instalación de GitLab con HTTPS", true, ELEMENTARY, teacher))));
    }

    @Test
    public void findByTeacherId2AndNameContainingAllIgnoringCaseFindingByGitLabShouldReturnTwoCoursesContentTheirs() {
        Teacher teacher = teacherRepository.findById(2L);

        Page<Course> courses = sut.findByTeacherIdAndNameContainingAllIgnoringCase(2L, "gitlab", PageRequest.of(0, 3, Direction.ASC, "name"));

        assertThat(courses.getTotalElements(), equalTo(2L));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.equals(new Course(3L, "Instalación de GitLab con HTTPS", true, ELEMENTARY, teacher))));
    }

    @Test
    @Transactional
    public void saveShouldSaveTestCourse() {
        Teacher teacher = teacherRepository.findById(1L);
        Course courseToSave = new Course("test", true, ELEMENTARY, teacher);

        sut.save(courseToSave);

        Page<Course> courses = sut.findByTeacherId(1L, PageRequest.of(0, 100, Sort.Direction.ASC, "name"));
        assertTrue(courses.getContent().stream().anyMatch(course -> course.equals(courseToSave)));
    }

}
