package com.cdelhoyo.cursosboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Subject  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "subject_generator", sequenceName = "subject_sequence", initialValue = 100)
    @GeneratedValue(generator = "subject_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Course course;

    protected Subject() {
    }

    public Subject(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public Subject(Long id, String name, Course course) {
        this(name, course);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return new EqualsBuilder().append(id, subject.id).append(name, subject.name).append(course, subject.course).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(course).toHashCode();
    }

}
