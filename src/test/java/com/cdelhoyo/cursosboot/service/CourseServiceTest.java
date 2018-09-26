package com.cdelhoyo.cursosboot.service;

import com.cdelhoyo.cursosboot.data.CourseRepository;
import com.cdelhoyo.cursosboot.data.SubjectRepository;
import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Subject;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class CourseServiceTest {

    private CourseRepository courseRepository = mock(CourseRepository.class);
    private SubjectRepository subjectRepository = mock(SubjectRepository.class);
    private CourseService sut = new CourseServiceImpl(courseRepository, subjectRepository);

    @Test
    public void findCoursesWithoutNameShouldCallFindAll() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(courseRepository).findAll(pageable);

        Page<Course> result = sut.findCourses(null, pageable);

        verify(courseRepository).findAll(pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findCoursesWithNameShouldCallFindByNameContainingAllIgnoringCase() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(courseRepository).findByNameContainingAllIgnoringCase("test", pageable);

        Page<Course> result = sut.findCourses("test", pageable);

        verify(courseRepository).findByNameContainingAllIgnoringCase("test", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getCourseByIdShouldCallFindById() {
        Course expected = mock(Course.class);
        doReturn(expected).when(courseRepository).findById(1L);

        Course result = sut.getCourseById(1L);

        verify(courseRepository).findById(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getSubjectsShouldCallGetSubjects() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(subjectRepository).findByCourseId(1L, pageable);

        Page<Subject> result = sut.getSubjects(1L, pageable);

        verify(subjectRepository).findByCourseId(1L, pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addSubject() {
        Subject expected = mock(Subject.class);
        Course course = mock(Course.class);
        Subject subject = new Subject("test", course);
        doReturn(course).when(courseRepository).findById(1L);
        doReturn(expected).when(subjectRepository).save(subject);

        Subject result = sut.addSubject(1L, "test");

        verify(subjectRepository).save(subject);
        assertThat(result, equalTo(expected));
    }
}