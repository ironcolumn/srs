package com.srs.service.course;

import com.srs.dao.CourseRepository;
import com.srs.domain.CourseCatalog;
import com.srs.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Course findCourseById ( Integer id ) {

        Optional < Course > courseOptional = courseRepository.findById ( id );
        return courseOptional.orElse ( null );
    }
}
