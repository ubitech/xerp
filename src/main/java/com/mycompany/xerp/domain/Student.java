/*
 * Copyright 2016 PaaSword Framework, http://www.paasword.eu/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.xerp.domain;

import eu.paasword.annotations.PaaSwordEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 *
 * @author Panagiotis Gouvas (pgouvas@ubitech.eu)
 */
@PaaSwordEntity
public class Student {
    
//    public static final String DROP_TABLE_STUDENTS = "DROP TABLE IF EXISTS students CASCADE";
//    public static final String CREATE_TABLE_STUDENTS = "CREATE TABLE students(id int primary key, name char(50) not null, surname char(50) not null, birth_date Date not null, gender char(1) not null,  semester int not null, grade double precision, fk_university int references Unis, fk_faculty int references faculties);";
//    public static final String INSERT_INTO = "INSERT INTO students (id, name, surname, birth_date, gender, semester, grade, fk_university, fk_faculty) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s);";
//    public static final String TABLE_NAME = "students";
   
    @Id    
    @Column    
    private long id;
    
    @Column (nullable = false,length = 50)        
    private String name;

    @Column (nullable = false,length = 50)        
    private String surname;
    
    @Column (nullable = false)        
    private Date birth_date;
    
    @Column (nullable = false)        
    private boolean gender;
    
    @Column (nullable = false)         
    private int semester;
    
    @Column        
    private double grade;
    
    @ManyToOne
    private University university;
    
    @ManyToOne    
    private Faculty faculty;

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
        
} //EoC
