package com.example.antraTrainingSpringRest.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.antraTrainingSpringRest.model.ClassUnit;


public interface ClassDao extends JpaRepository<ClassUnit, Long> {


}
