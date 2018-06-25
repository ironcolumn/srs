package com.srs.service.course;

import com.srs.dao.CourseRepository;
import com.srs.domain.CourseCatalog;
import com.srs.po.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private final CourseCatalog catalog = CourseCatalog.getInstance ( );

    @Override
    public String findAllInJson ( ) {

        List < Course > courses = courseRepository.findAll ( );
        return catalog.toJSON ( courses );
    }

    @Override
    @Transactional ( rollbackFor = Exception.class )
    public Course saveOne ( Course course ) {
        //        for (int i = 0; i < course.getPrevCourses().size(); i++) {
        //
        //            Optional<Course> coursePo = courseRepository.findById(course.getPrevCourses().get(i).getId());
        //            if (coursePo.isPresent()) {
        //                course.getPrevCourses().set(i, coursePo.get());
        //            }
        //        }
        return courseRepository.save ( course );

    }

    @Override
    public List < Course > saveAll ( List < Course > courses ) {

        return courseRepository.saveAll ( courses );
    }

    @Override
    public boolean deleteOne ( Course course ) {

        courseRepository.delete ( course );
        return true;
    }

    @Override
    public boolean deleteOne ( Integer id ) {

        courseRepository.deleteById ( id );
        return true;
    }
}
