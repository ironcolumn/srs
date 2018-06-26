package com.srs.dao;

import com.srs.po.Course;
import com.srs.po.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository < Student, Integer > {

    boolean existsByIdEqualsAndStudyPlanContains ( Integer id , Course course );

}
