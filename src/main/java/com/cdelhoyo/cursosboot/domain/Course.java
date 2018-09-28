package com.cdelhoyo.cursosboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Course  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "course_generator", sequenceName = "course_sequence", initialValue = 100)
    @GeneratedValue(generator = "course_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Level level;

    @ManyToOne(optional = false)
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Subject> subjects;

    protected Course() {
    }

    public Course(String name, Boolean active, Level level, Teacher teacher) {
        this.name = name;
        this.active = active;
        this.level = level;
        this.teacher = teacher;
    }

    public Course(Long id, String name, Boolean active, Level level, Teacher teacher) {
        this(name, active, level, teacher);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public Level getLevel() {
        return level;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return new EqualsBuilder().append(id, course.id).append(name, course.name).append(active, course.active).append(level, course.level).append(teacher, course.teacher).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(active).append(level).append(teacher).toHashCode();
    }

}
