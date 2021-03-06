package com.srs.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.srs.model.Transcript;

import java.util.List;

public class TranscriptCatalog {

    private static final TranscriptCatalog INSTANCE = new TranscriptCatalog ( );

    public String getTranscriptJsonForStudent ( List < Transcript > transcripts ) {

        ObjectMapper mapper    = new ObjectMapper ( );
        ArrayNode    arrayNode = mapper.createArrayNode ( );
        for ( Transcript transcript : transcripts ) {
            ObjectNode objectNode = mapper.convertValue ( transcript , ObjectNode.class );
            objectNode.put ( "sectionSn" , transcript.getSection ( ).getSectionSn ( ) );
            objectNode.put ( "courseName" , transcript.getSection ( ).getCourse ( ).getCourseName ( ) );
            objectNode.put ( "credit" , transcript.getSection ( ).getCourse ( ).getCredit ( ) );
            objectNode.put ( "teacherName" , transcript.getSection ( ).getProfessor ( ).getName ( ) );
            objectNode.put ( "room" , transcript.getSection ( ).getRoom ( ) );
            objectNode.put ( "dayOfWeek" , transcript.getSection ( ).getDayOfWeek ( ) );
            objectNode.put ( "timeOfDay" , transcript.getSection ( ).getTimeOfDay ( ) );
            if ( transcript.getGrade ( ) != null ) {
                objectNode.put ( "grade" , transcript.getGrade ( ) );
                objectNode.put ( "state" , "已选" );
            } else {
                int     index    = transcript.getSection ( ).getTranscripts ( ).indexOf ( transcript );
                Integer capacity = transcript.getSection ( ).getCapacity ( );
                if ( index < capacity ) {
                    objectNode.put ( "state" , "已选" );
                } else {
                    objectNode.put ( "state" , "队列中" );
                }
            }
            arrayNode.add ( objectNode );
        }
        return arrayNode.toString ( );
    }

    public String getTranscriptJsonForStudentGraded ( List < Transcript > transcripts ) {

        ObjectMapper mapper    = new ObjectMapper ( );
        ArrayNode    arrayNode = mapper.createArrayNode ( );
        for ( Transcript transcript : transcripts ) {
            if ( transcript.getGrade ( ) != null ) {
                ObjectNode objectNode = mapper.convertValue ( transcript , ObjectNode.class );
                objectNode.put ( "sectionSn" , transcript.getSection ( ).getSectionSn ( ) );
                objectNode.put ( "courseName" , transcript.getSection ( ).getCourse ( ).getCourseName ( ) );
                objectNode.put ( "credit" , transcript.getSection ( ).getCourse ( ).getCredit ( ) );
                objectNode.put ( "teacherName" , transcript.getSection ( ).getProfessor ( ).getName ( ) );
                objectNode.put ( "room" , transcript.getSection ( ).getRoom ( ) );
                objectNode.put ( "dayOfWeek" , transcript.getSection ( ).getDayOfWeek ( ) );
                objectNode.put ( "timeOfDay" , transcript.getSection ( ).getTimeOfDay ( ) );
                objectNode.put ( "grade" , transcript.getGrade ( ) );
                objectNode.put ( "state" , "已选" );
                arrayNode.add ( objectNode );
            }
        }
        return arrayNode.toString ( );
    }

    public String getTranscriptJsonForTeacher ( List < Transcript > transcripts ) {

        ObjectMapper mapper    = new ObjectMapper ( );
        ArrayNode    arrayNode = mapper.createArrayNode ( );
        for ( Transcript transcript : transcripts ) {
            ObjectNode objectNode = mapper.convertValue ( transcript , ObjectNode.class );
            objectNode.put ( "name" , transcript.getStudent ( ).getName ( ) );
            objectNode.put ( "studentSn" , transcript.getStudent ( ).getStudentSn ( ) );
            objectNode.put ( "major" , transcript.getStudent ( ).getMajor ( ) );
            objectNode.put ( "degree" , transcript.getStudent ( ).getDegree ( ) );
            objectNode.put ( "grade" , transcript.getGrade ( ) );
            ObjectNode student = mapper.createObjectNode ( );
            student.put ( "id" , transcript.getStudent ( ).getId ( ) );
            objectNode.set ( "student" , student );
            ObjectNode section = mapper.createObjectNode ( );
            section.put ( "id" , transcript.getSection ( ).getId ( ) );
            objectNode.set ( "section" , section );
            arrayNode.add ( objectNode );
        }
        return arrayNode.toString ( );
    }

    public static TranscriptCatalog getInstance ( ) {

        return INSTANCE;
    }

    private TranscriptCatalog ( ) {

    }
}
