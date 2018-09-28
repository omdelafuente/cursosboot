package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Subject;
import com.cdelhoyo.cursosboot.service.CourseService;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CourseControllerTest {

    private CourseService courseService = mock(CourseService.class);
    private CourseController sut = new CourseController(courseService);

    @Test
    public void findCoursesShouldCallServiceFindCoursesAndReturnResult(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(courseService).findCourses("testName", pageable);

        Page<Course> result = sut.findCourses("testName", pageable);

        verify(courseService).findCourses("testName", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getShouldCallServiceGetAndReturnResult(){
        Course expected = mock(Course.class);
        doReturn(expected).when(courseService).get(1L);

        Course result = sut.get(1L);

        verify(courseService).get(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findSubjectsShouldCallServiceFindSubjectsAndReturnResult(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(courseService).findSubjects(1L, "testName", pageable);

        Page<Subject> result = sut.findSubjects(1L, "testName", pageable);

        verify(courseService).findSubjects(1L, "testName", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addSubjectShouldCallServiceAddSubjectAndReturnResult(){
        Subject subject = mock(Subject.class);
        Subject expected = mock(Subject.class);
        doReturn(expected).when(courseService).addSubject(1L, subject);

        Subject result = sut.addSubject(1L, subject);

        verify(courseService).addSubject(1L, subject);
        assertThat(result, equalTo(expected));
    }
}
