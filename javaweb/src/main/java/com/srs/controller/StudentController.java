package com.srs.controller;

import com.srs.bind.CurrentUser;
import com.srs.po.Course;
import com.srs.po.Student;
import com.srs.po.SysUser;
import com.srs.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping ( value = "students", produces = { APPLICATION_JSON_UTF8_VALUE } )
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping ( value = "studyPlans" )
    public ResponseEntity getStudyPlans ( @CurrentUser SysUser sysUser ) {

        if ( sysUser == null ) {
            return ResponseEntity.badRequest ( ).body ( "{'msg','无法获取当前登陆用户'}" );
        } else if ( sysUser.getStudent ( ) == null ) {
            return ResponseEntity.badRequest ( ).body ( "{'msg','当前登陆用户不是学生'}" );
        }
        Student student = studentService.findStudentById ( sysUser.getStudent ( ).getId ( ) );
        return ResponseEntity.ok ( student.getStudyPlan ( ) );
    }

    @PostMapping ( value = "studyPlans" )
    public ResponseEntity addOneStudyPlanRecord ( @RequestBody Course course , @CurrentUser SysUser sysUser ) {

        if ( sysUser == null ) {
            return ResponseEntity.badRequest ( ).body ( "{'msg','无法获取当前登陆用户'}" );
        } else if ( sysUser.getStudent ( ) == null ) {
            return ResponseEntity.badRequest ( ).body ( "{'msg','当前登陆用户不是学生'}" );
        }
        Student student = studentService.findStudentById ( sysUser.getStudent ( ).getId ( ) );
        return ResponseEntity.ok ( student.getStudyPlan ( ).add ( course ) );
    }

    @DeleteMapping ( value = "studyPlans/{id}" )
    public ResponseEntity deleteOneStudyPlanRecord ( @PathVariable Integer id , @CurrentUser SysUser sysUser ) {

        if ( sysUser == null ) {
            return ResponseEntity.badRequest ( ).body ( "{'msg','无法获取当前登陆用户'}" );
        } else if ( sysUser.getStudent ( ) == null ) {
            return ResponseEntity.badRequest ( ).body ( "{'msg','当前登陆用户不是学生'}" );
        }
        Student student = studentService.findStudentById ( sysUser.getStudent ( ).getId ( ) );
        return ResponseEntity.ok ( studentService.deleteOneStudentPlanRecord ( id ,student));
    }

}
