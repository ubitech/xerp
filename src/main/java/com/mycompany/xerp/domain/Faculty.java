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

/**
 *
 * @author Panagiotis Gouvas (pgouvas@ubitech.eu)
 */
@PaaSwordEntity
public class Faculty {
    
//    public static final String CREATE_TABLE_FACULTY = "CREATE TABLE faculties (id int primary key, name char(50) not null);";
//    public static final String DROP_TABLE_FACULTY = "DROP TABLE IF EXISTS faculties CASCADE;";
//    private static final String INSERT_INTO ="INSERT INTO faculties (id, name) VALUES (%s, %s);";
//    public static final String TABLE_NAME = "faculties";

    @Id    
    @Column       
    private long id;
    
    @Column (nullable = false,length = 50)       
    private String name;    

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
        
}
