package com.srs.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table ( uniqueConstraints = @UniqueConstraint ( columnNames = { "professorSn" } ) )
@JsonIgnoreProperties ( value = { "hibernateLazyInitializer" , "handler" , "fieldHandler" } )
public class Professor implements Serializable {

    private Integer id;

    private String professorSn;

    private String title;

    private String department;

    private String name;

    @JsonManagedReference ( value = "professor-section" )
    private List < Section > sections;

    private SysUser user;

    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    public Integer getId ( ) {

        return id;
    }

    public void setId ( Integer id ) {

        this.id = id;
    }

    public String getprofessorSn ( ) {

        return professorSn;
    }

    public void setprofessorSn ( String professorSn ) {

        this.professorSn = professorSn;
    }

    public String getTitle ( ) {

        return title;
    }

    public void setTitle ( String title ) {

        this.title = title;
    }

    public String getDepartment ( ) {

        return department;
    }

    public void setDepartment ( String department ) {

        this.department = department;
    }

    public String getName ( ) {

        return name;
    }

    public void setName ( String name ) {

        this.name = name;
    }

    @OneToMany ( cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "professor" )
    public List < Section > getSections ( ) {

        return sections;
    }

    public void setSections ( List < Section > sections ) {

        this.sections = sections;
    }

    @OneToOne ( cascade = { CascadeType.ALL }, fetch = FetchType.LAZY )
    @JoinColumn ( name = "userId", referencedColumnName = "id" )
    public SysUser getUser ( ) {

        return user;
    }

    public void setUser ( SysUser user ) {

        this.user = user;
    }
}
