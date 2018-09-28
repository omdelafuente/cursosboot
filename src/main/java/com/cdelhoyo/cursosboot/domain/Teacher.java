package com.cdelhoyo.cursosboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Teacher  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "teacher_generator", sequenceName = "teacher_sequence", initialValue = 100)
    @GeneratedValue(generator = "teacher_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return new EqualsBuilder().append(id, teacher.id).append(name, teacher.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).toHashCode();
    }

}
