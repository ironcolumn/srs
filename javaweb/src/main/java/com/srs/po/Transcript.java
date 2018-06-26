package com.srs.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.srs.specification.PrevCourseSpecification;
import com.srs.specification.SelectOnceSpecification;
import com.srs.specification.Specification;
import com.srs.specification.StudyPlanSpecification;

import javax.persistence.*;

@Entity
public class Transcript {

    private Integer id;

    @JsonBackReference(value = "transcripts-section")
    private Section section;

    @JsonBackReference(value = "transcripts-student")
    private Student student;

    private Integer grade;

    private final Specification<Transcript> selectOnce = new SelectOnceSpecification ();

    private final Specification<Transcript> prevCourse = new PrevCourseSpecification ();

    private final Specification<Transcript> studyPlan = new StudyPlanSpecification ();

    public Transcript() {
    }

    public Transcript(Section section, Student student) {
        this.section = section;
        this.student = student;
    }

    public String canChoose() {
        if (!selectOnce.isSatisfiedBy(this)) {
            return "{\"msg\":\"您已经选过这门课\"}";
        }
        if (!prevCourse.isSatisfiedBy(this)) {
            return "{\"msg\":\"您必须先修完所有先修课\"}";
        }
        if (!studyPlan.isSatisfiedBy(this)) {
            return "{\"msg\":\"选课不符合您的学习计划\"}";
        }
        return null;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne (cascade = { CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn (name = "sectionId", referencedColumnName = "id")
    public Section getSection() {
        return section;
    }


    public void setSection(Section section) {
        this.section = section;
    }

    @ManyToOne (cascade = { CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn (name = "studentId", referencedColumnName = "id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
