package com.srs.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.srs.model.Section;

import java.util.List;

public class SectionCatalog {

    private static final SectionCatalog INSTANCE = new SectionCatalog();

    public String getCourseArrangeJson (List<Section > sections ) {
        ObjectMapper mapper    = new ObjectMapper ();
        ArrayNode    arrayNode = mapper.createArrayNode ( );
        for (Section section : sections) {
            ObjectNode objectNode = mapper.convertValue (section, ObjectNode.class );
            objectNode.put("courseId", section.getCourse().getId());
            objectNode.put("courseSn", section.getCourse().getCourseSn());
            objectNode.put("courseName", section.getCourse().getCourseName());
            objectNode.put("credit", section.getCourse().getCredit());
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }

    public String getSectionJson(List<Section> sections) {
        ObjectMapper mapper    = new ObjectMapper ();
        ArrayNode    arrayNode = mapper.createArrayNode ( );
        for (Section section : sections) {
            ObjectNode objectNode = mapper.convertValue (section, ObjectNode.class );
            int        size       = section.getTranscripts().size();
            objectNode.put("courseId", section.getCourse().getId());
            objectNode.put("courseSn", section.getCourse().getCourseSn());
            objectNode.put("courseName", section.getCourse().getCourseName());
            objectNode.put("credit", section.getCourse().getCredit());
            objectNode.put("capacityWithFraction", size + "/" + section.getCapacity());
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }

    public List<Section> getTeacherSectionAvailable(List<Section> sections){
        sections.removeIf (section -> section.getProfessor ( ) != null );
        return sections;
    }

    public static SectionCatalog getInstance() {
        return INSTANCE;
    }

    private SectionCatalog() {
    }
}
