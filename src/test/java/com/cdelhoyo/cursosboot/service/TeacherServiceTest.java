package com.cdelhoyo.cursosboot.service;

import com.cdelhoyo.cursosboot.data.CourseRepository;
import com.cdelhoyo.cursosboot.data.TeacherRepository;
import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Level;
import com.cdelhoyo.cursosboot.domain.Teacher;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeacherServiceTest {

    private TeacherRepository teacherRepository = mock(TeacherRepository.class);
    private CourseRepository courseRepository = mock(CourseRepository.class);
    private TeacherService sut = new TeacherServiceImpl(teacherRepository, courseRepository);

    @Test
    public void findTeachersWithoutNameShouldCallFindAllAndReturnTheirs() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(teacherRepository).findAll(pageable);

        Page<Teacher> result = sut.findTeachers(null, pageable);

        verify(teacherRepository).findAll(pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findTeachersWithNameShouldCallFindByNameContainingAllIgnoringCaseAndReturnTheirs() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(teacherRepository).findByNameContainingAllIgnoringCase("test", pageable);

        Page<Teacher> result = sut.findTeachers("test", pageable);

        verify(teacherRepository).findByNameContainingAllIgnoringCase("test", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void getTeacherByIdShouldCallFindById() {
        Teacher expected = mock(Teacher.class);
        doReturn(expected).when(teacherRepository).findById(1L);

        Teacher result = sut.get(1L);

        verify(teacherRepository).findById(1L);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addShouldCallToSave() {
        Teacher expected = mock(Teacher.class);
        Teacher teacher = mock(Teacher.class);
        doReturn(expected).when(teacherRepository).save(teacher);

        Teacher result = sut.add(teacher);

        verify(teacherRepository).save(teacher);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findCoursesWithoutNameShouldCallFindByTeacherAndReturnTheirs() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(courseRepository).findByTeacherId(1L, pageable);

        Page<Course> result = sut.findCourses(1L, null, pageable);

        verify(courseRepository).findByTeacherId(1L, pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void findCoursesWithNameShouldCallFindByTeacherIdAndNameContainingAllIgnoringCaseAndReturnTheirs() {
        Pageable pageable = mock(Pageable.class);
        Page expected = mock(Page.class);
        doReturn(expected).when(courseRepository).findByTeacherIdAndNameContainingAllIgnoringCase(1L, "test", pageable);

        Page<Course> result = sut.findCourses(1L, "test", pageable);

        verify(courseRepository).findByTeacherIdAndNameContainingAllIgnoringCase(1L, "test", pageable);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void addCourseShouldCallToSave() {
        Course expected = mock(Course.class);
        Teacher teacher = mock(Teacher.class);
        Course course = new Course("test", true, Level.ELEMENTARY, teacher);
        doReturn(teacher).when(teacherRepository).findById(1L);
        doReturn(expected).when(courseRepository).save(course);

        Course result = sut.addCourse(1L, new Course("test", true, Level.ELEMENTARY, null));

        verify(courseRepository).save(course);
        assertThat(result, equalTo(expected));
    }

}
