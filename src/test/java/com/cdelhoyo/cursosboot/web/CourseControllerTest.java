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
    public void getAllShouldCallFindCoursesWithNullName(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(courseService).findCourses(null, pageable);

        Page<Course> result = sut.getAll(pageable);

        verify(courseService).findCourses(null, pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getShouldCallGetCourseById(){
        Course expected = mock(Course.class);
        doReturn(expected).when(courseService).getCourseById(1L);

        Course result = sut.get(1L);

        verify(courseService).getCourseById(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getAllByNameShouldCallFindCoursesWithTestName(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(courseService).findCourses("test", pageable);

        Page<Course> result = sut.getAllByName("test", pageable);

        verify(courseService).findCourses("test", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getSubjectsShouldCallGetSubjects(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(courseService).getSubjects(1L, pageable);

        Page<Subject> result = sut.getSubjects(1L, pageable);

        verify(courseService).getSubjects(1L, pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addSubjectShouldCallGetCourseById(){
        Subject expected = mock(Subject.class);
        doReturn(expected).when(courseService).addSubject(1L, "test");

        Subject result = sut.addSubject(1L, "test");

        verify(courseService).addSubject(1L, "test");
        assertThat(result, equalTo(expected));
    }
}
