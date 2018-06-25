package com.srs.dao;

import com.srs.po.course.Course;
import com.srs.po.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository < Student, Integer > {

    boolean existsByIdEqualsAndStudyPlanContains ( Integer id , Course course );

}
