package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.CourseSummary;
import com.cdelhoyo.cursosboot.domain.Teacher;
import com.cdelhoyo.cursosboot.domain.Subject;
import com.cdelhoyo.cursosboot.service.TeacherService;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TeacherControllerTest {

    private TeacherService teacherService = mock(TeacherService.class);
    private TeacherController sut = new TeacherController(teacherService);

    @Test
    public void getAllShouldCallFindTeachersWithNullName(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).findTeachers(null, pageable);

        Page<Teacher> result = sut.getAll(pageable);

        verify(teacherService).findTeachers(null, pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getShouldCallGetTeacherById(){
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherService).getTeacherById(1L);

        Teacher result = sut.get(1L);

        verify(teacherService).getTeacherById(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getAllByNameShouldCallFindTeachersWithTestName(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).findTeachers("test", pageable);

        Page<Teacher> result = sut.getAllByName("test", pageable);

        verify(teacherService).findTeachers("test", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getCoursesShouldCallGetCourses(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).getCourses(1L, pageable);

        Page<CourseSummary> result = sut.getCourses(1L, pageable);

        verify(teacherService).getCourses(1L, pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getCoursesByNameShouldCallGetCoursesByTeacherIdAndName(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).getCoursesByTeacherIdAndName(1L, "test", pageable);

        Page<Course> result = sut.getCoursesByName(1L, "test", pageable);

        verify(teacherService).getCoursesByTeacherIdAndName(1L, "test", pageable);
        assertThat(result, equalTo(expected));
    }

}
