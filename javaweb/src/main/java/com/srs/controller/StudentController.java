package com.srs.controller;

import com.srs.domain.Student;
import com.srs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzy on 2018/6/21 23:44.
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping ( "/student" )
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping ( "queryAll" )
    @ResponseBody
    public List < Student > queryAll ( ) {

        return studentService.getAll ( );
    }
}
