package com.srs.domain;

import javax.persistence.*;

/**
 * @author zzy on 2018/6/21 23:41.
 * @version 1.0
 */
@Entity
@Table ( name = "student" )
public class Student {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    @Column ( name = "id" )
    private String id;

    @Column ( name = "name" )
    private String name;

    @Column ( name = "gender" )
    private String gender;

    @Column ( name = "age" )
    private int age;
}
