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
 * @author Panagiotis Gouvas
 */
@PaaSwordEntity
public class Country {
//    public static final String CREATE_TABLE_COUNTRY = "CREATE TABLE countries (id int primary key, name char(50) not null, inhabitants int not null)";
//    public static final String DROP_TABLE_COUNTRY = "DROP TABLE IF EXISTS countries CASCADE;";
//    private static final String INSERT_INTO = "INSERT INTO countries (id, name, inhabitants) VALUES (%s, %s, %s);";
//    public static final String TABLE_NAME = "countries";
    @Id
    @Column 
    private long id;

    @Column (nullable = false,length = 50)     
    private String name;

    @Column (nullable = false)    
    private int inhabitants;

//    @ManyToOne            //cyclic dependency just for test
//    private City city;
    
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

    public int getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
    }
        
}//EoC
