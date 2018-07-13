package com.srs;

import com.srs.application.DemoApplication;
import com.srs.dao.CourseRepository;
import com.srs.dao.StudentRepository;
import com.srs.model.Course;
import com.srs.service.course.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Rollback (value = false)
@Transactional
public class SrsApplicationTests {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void testCourse ( ) {

        Course course = new Course ( );
        course.setId ( 7 );
        course.setCourseSn ( "7" );
        course.setCourseName ( "测试课程" );
        course.setCredit ( 2 );
        course.setPrevCourses (new ArrayList <> () );
        courseService.saveOne ( course );

    }

}
