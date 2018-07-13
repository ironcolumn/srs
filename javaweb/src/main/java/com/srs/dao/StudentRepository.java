package com.srs.dao;

import com.srs.model.Course;
import com.srs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository < Student, Integer > {

    boolean existsByIdEqualsAndStudyPlanContains ( Integer id , Course course );

}
