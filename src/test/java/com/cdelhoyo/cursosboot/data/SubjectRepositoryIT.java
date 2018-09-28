package com.cdelhoyo.cursosboot.data;

import com.cdelhoyo.cursosboot.domain.Course;
import com.cdelhoyo.cursosboot.domain.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Rollback
public class SubjectRepositoryIT {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository sut;

    @Test
    public void findByCourseId1ShouldReturn3Subjects() {
        Course course = courseRepository.findById(1L);

        Page<Subject> subjects = sut.findByCourseId(1L, PageRequest.of(0, 3, Sort.Direction.ASC, "name"));

        assertThat(subjects.getTotalElements(), equalTo(3L));
        assertTrue(subjects.getContent().stream().anyMatch(subject -> subject.equals(new Subject(2L, "Kanban", course))));
    }
    @Test
    public void findByCourseId1AndNameScrumContainingAllIgnoringCaseShouldReturn1Subject() {
        Course course = courseRepository.findById(1L);

        Page<Subject> subjects = sut.findByCourseIdAndNameContainingAllIgnoringCase(1L, "scrum", PageRequest.of(0, 3, Sort.Direction.ASC, "name"));

        assertThat(subjects.getTotalElements(), equalTo(1L));
        assertTrue(subjects.getContent().stream().anyMatch(subject -> subject.equals(new Subject(1L, "Scrum", course))));
    }

    @Test
    @Transactional
    public void saveShouldSaveTestSubject() {
        Course course = courseRepository.findById(1L);
        Subject subjectToSave = new Subject("test", course);

        sut.save(subjectToSave);

        Page<Subject> subjects = sut.findByCourseId(1L, PageRequest.of(0, 5, Sort.Direction.ASC, "name"));
        assertTrue(subjects.getContent().stream().anyMatch(subject -> subject.equals(subjectToSave)));
    }
}