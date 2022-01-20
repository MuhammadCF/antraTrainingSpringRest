package com.example.antraTrainingSpringRest.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;



import javax.persistence.Entity;



import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "ClassUnit")
@Table(
        name = "classunit",
        uniqueConstraints = {
                @UniqueConstraint(name = "class_code_unique", columnNames = "class_code")
        }
)
public class ClassUnit {



    @Id
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "class_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;


    @Column(
            name = "class_code",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String classCode;

    @Column(
            name = "class_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String className;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @OneToMany(
            mappedBy = "classunit",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<StudentClassIntermediate> intermediates = new ArrayList<>();

    public void addStudentClassIntermediate(StudentClassIntermediate intermediate) {
        if (!this.intermediates.contains(intermediate)) {
            this.intermediates.add(intermediate);
            intermediate.setClassunit(this);
        }
    }
    public void removeStudentClassIntermediate(StudentClassIntermediate intermediate) {
        if (!this.intermediates.contains(intermediate)) {
            this.intermediates.remove(intermediate);
            intermediate.setClassunit(null);
        }
    }

    public List<StudentClassIntermediate> getIntermediates(){
        return intermediates;
    }

    public ClassUnit() {
    }

    public ClassUnit(Long id, String classCode, String className) {
        this.id = id;
        this.classCode = classCode;
        this.className = className;
    }



    @Override
    public String toString() {
        return "ClassUnit{" +
                "id=" + id +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}









