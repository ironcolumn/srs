package com.srs.service.student;

import com.srs.dao.StudentRepository;
import com.srs.po.Course;
import com.srs.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional ( rollbackFor = Exception.class )
    public List < Course > getStudyPlans ( Student student ) {

        if ( student != null ) {
            Optional < Student > studentInRepo = studentRepository.findById ( student.getId ( ) );
            if ( studentInRepo.isPresent ( ) ) {
                student = studentInRepo.get ( );
                return student.getStudyPlan ( );
            }
        }
        return new ArrayList <> ( 0 );
    }

    @Override
    public List < Course > addOneStudyPlanRecord ( Course course , Student student ) {

        student.getStudyPlan ( ).add ( course );
        studentRepository.save ( student );
        return student.getStudyPlan ( );
    }

    @Override
    public List < Course > deleteOneStudentPlanRecord ( Integer courseId , Student student ) {

        student.getStudyPlan ( ).removeIf ( course -> course.getId ( ).equals ( courseId ) );
        studentRepository.save ( student );
        return student.getStudyPlan ( );
    }

    @Override
    public Student findStudentById ( Integer id ) {

        Optional < Student > studentOptional = studentRepository.findById ( id );
        return studentOptional.orElse ( null );
    }
}
