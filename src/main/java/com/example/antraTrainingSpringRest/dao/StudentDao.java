package com.example.antraTrainingSpringRest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antraTrainingSpringRest.model.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
}
