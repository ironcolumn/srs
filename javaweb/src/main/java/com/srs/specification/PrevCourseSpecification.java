package com.srs.specification;

import com.srs.model.Course;
import com.srs.model.Transcript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PrevCourseSpecification implements Specification < Transcript > {

    @Override
    public boolean isSatisfiedBy ( Transcript transcript ) {

        List < Transcript > transcripts = transcript.getStudent ( ).getTranscripts ( );
        List < Course >     prevCourses = transcript.getSection ( ).getCourse ( ).getPrevCourses ( );
        //如果没成绩或没及格视为没学过
        Set < Course > courseSet = transcripts.stream ( )
                .filter ( t -> t.getGrade ( ) != null && t.getGrade ( ) >= 60 )
                .map ( t -> t.getSection ( ).getCourse ( ) )
                .collect ( Collectors.toSet ( ) );
        return ( courseSet.containsAll ( prevCourses ) );
    }
}
