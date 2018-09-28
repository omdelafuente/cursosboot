package com.cdelhoyo.cursosboot.web;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Teacher;
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
    public void findTeachersShouldCallServiceFindTeachersAndReturnResult(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).findTeachers("testName", pageable);

        Page<Teacher> result = sut.findTeachers("testName", pageable);

        verify(teacherService).findTeachers("testName", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getShouldCallServiceGetAndReturnResult(){
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherService).get(1L);

        Teacher result = sut.get(1L);

        verify(teacherService).get(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addShouldCallServiceAddAndReturnResult(){
        Teacher teacher = mock(Teacher.class);
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherService).add(teacher);

        Teacher result = sut.add(teacher);

        verify(teacherService).add(teacher);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findCoursesShouldCallServiceFindCoursesAndReturnResult(){
        Page expected = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        doReturn(expected).when(teacherService).findCourses(1L, "testName", pageable);

        Page<Course> result = sut.findCourses(1L, "testName", pageable);

        verify(teacherService).findCourses(1L, "testName", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addCourseShouldCallServiceAddCourseAndReturnResult(){
        Course course = mock(Course.class);
        Course expected = mock(Course.class);
        doReturn(expected).when(teacherService).addCourse(1L, course);

        Course result = sut.addCourse(1L, course);

        verify(teacherService).addCourse(1L, course);
        assertThat(result, equalTo(expected));
    }

}
