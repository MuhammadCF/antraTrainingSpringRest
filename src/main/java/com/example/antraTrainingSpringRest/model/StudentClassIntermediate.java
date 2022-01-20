package com.example.antraTrainingSpringRest.model;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "StudentClassIntermediate")
@Table(name = "studentclassintermediate")
public class StudentClassIntermediate {

    @Id
    @SequenceGenerator(
            name = "studentclassintermediate_sequence",
            sequenceName = "studentclassintermediate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "studentclassintermediate_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "class_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "intermediate_class_fk"
            )
    )
    private ClassUnit classunit;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "intermediate_student_fk"
            )
    )
    private Student student;

    public StudentClassIntermediate() {
    }

    public Long getId() {
        return id;
    }

    public ClassUnit getClassunit() {
        return classunit;
    }

    public void setClassunit(ClassUnit classunit) {
        this.classunit = classunit;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentClassIntermediate{" +
                "id=" + id +
                ", classunit=" + classunit +
                ", student=" + student +
                '}';
    }
}
