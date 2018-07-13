package com.srs;

import com.srs.dao.CourseRepository;
import com.srs.dao.StudentRepository;
import com.srs.model.Course;
import com.srs.model.Section;
import com.srs.model.Student;
import com.srs.service.course.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SrsTests {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void testSelectCourse ( ) {
        //new test course1
        Course course1 = new Course ( );
        course1.setId ( 1 );
        course1.setCourseName ( "测试课程1" );
        course1.setCredit ( 1 );
        course1.setCourseSn ( "001" );
        course1.setPrevCourses ( new ArrayList <> ( ) );

        //new test course2
        Course course2 = new Course ( );
        course2.setId ( 2 );
        course2.setCourseName ( "测试课程1的先修课" );
        course2.setCredit ( 2 );
        course2.setCourseSn ( "002" );
        course2.setPrevCourses ( new ArrayList <> ( ) );

        //set pre-course
        course1.getPrevCourses ( ).add ( course2 );

        //new section1
        Section section1 = new Section ( );
        section1.setId ( 1 );
        section1.setCapacity ( 3 );
        section1.setCourse ( course1 );
        List < Section > section1List = new ArrayList <> ( );
        section1List.add ( section1 );
        course1.setSections ( section1List );

        //new section2
        Section section2 = new Section ( );
        section2.setId ( 2 );
        section2.setCapacity ( 3 );
        section2.setCourse ( course2 );
        List < Section > section2List = new ArrayList <> ( );
        section2List.add ( section2 );
        course2.setSections ( section2List );

        //new student
        Student student = new Student ( );
        student.setId ( 1 );
        student.setTranscripts ( new ArrayList <> ( ) );
        List < Course > studyPlan = new ArrayList <> ( );
        student.setStudyPlan ( studyPlan );
        student.getStudyPlan ( ).add ( course1 );

        //测试开始，首先选择section1
        System.out.println ( "选择section1" );
        student.chooseOneSection ( section1 );
        student.showSelectedSections ( );
        //选择先修课section2
        System.out.println ( "选择section2" );
        student.chooseOneSection ( section2 );
        student.showSelectedSections ( );
        //将course2加入学习计划
        student.getStudyPlan ( ).add ( course2 );
        // 再次选择section2
        System.out.println ( "再次选择section2" );
        student.chooseOneSection ( section2 );
        student.showSelectedSections ( );
        //给一个成绩
        student.getTranscript ( section2 ).setGrade ( 99 );
        //再次尝试选择section1
        System.out.println ( "再次选择section1" );
        student.chooseOneSection ( section1 );
        student.showSelectedSections ( );

    }
}
