package com.srs.service;

import com.srs.dao.StudentRepository;
import com.srs.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzy on 2018/6/21 23:45.
 * @version 1.0
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List < Student > getAll ( ) {

        return studentRepository.findAll ( );
    }
}
