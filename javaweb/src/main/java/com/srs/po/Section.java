package com.srs.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (uniqueConstraints=@UniqueConstraint (columnNames = { "sectionSn"}))
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Section implements Serializable {

    private Integer id;

    private String sectionSn;

    private String dayOfWeek;

    private String timeOfDay;

    private String room;

    private Integer capacity;

    @JsonBackReference(value = "course-section")
    private Course    course;
    @JsonBackReference(value = "professor-section")
    private Professor professor;

    @JsonManagedReference(value = "transcripts-section")
    private List<Transcript> transcripts;


    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @ManyToOne (cascade = { CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn (name = "courseId", referencedColumnName = "id")
    public Course getCourse ( ) {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne (cascade = { CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn (name = "professorId", referencedColumnName = "id")
    public Professor getProfessor ( ) {
        return professor;
    }

    public void setProfessor ( Professor professor ) {
        this.professor = professor;
    }

    @OneToMany (cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "section")
    public List<Transcript > getTranscripts ( ) {
        return transcripts;
    }

    public void setTranscripts(List<Transcript> transcripts) {
        this.transcripts = transcripts;
    }

    public String getSectionSn() {
        return sectionSn;
    }

    public void setSectionSn(String sectionSn) {
        this.sectionSn = sectionSn;
    }
}
