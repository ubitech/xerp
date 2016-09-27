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

/**
 *
 * @author Panagiotis Gouvas (pgouvas@ubitech.eu)
 */
@PaaSwordEntity
public class University {

//    private static final String INSERT_INTO = "INSERT INTO unis (id, name, number_of_lecutre_halls, fk_city) VALUES(%s, %s, %s, %s)";
//    public static final String CREATE_TABLE_UNIS =  "CREATE TABLE unis(id int primary key, name char(50) not null, number_of_lecutre_halls int, fk_city int references Cities);";
//    public static final String DROP_TABLE_UNIS = "DROP TABLE IF EXISTS unis CASCADE;";
//    public static final String TABLE_NAME = "unis";

    @Id
    @Column   
    private  long id;
    
    @Column(length = 50, nullable = false)
    private  String name;
    
    @Column
    private  int number_of_lecutre_halls;
    
    @ManyToOne
    private City city;


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

    public int getNumber_of_lecutre_halls() {
        return number_of_lecutre_halls;
    }

    public void setNumber_of_lecutre_halls(int number_of_lecutre_halls) {
        this.number_of_lecutre_halls = number_of_lecutre_halls;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
        
}
