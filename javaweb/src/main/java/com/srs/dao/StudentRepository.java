package com.srs.dao;

import com.srs.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zzy on 2018/6/21 23:42.
 * @version 1.0
 */
public interface StudentRepository extends JpaRepository < Student, String > {

}
