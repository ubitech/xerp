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
package com.mycompany.xerp.authentication;

import com.mycompany.xerp.domain.UserRole;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author smantzouratos
 */
public class CurrentUser extends User {

    public final Long userID;
    private String username;
    private final int roleID;
    private final String firstName;
    private final String lastName;

    public CurrentUser(Long userID, String username, String password, UserRole role, String firstName, String lastName) {
        super(username, password, AuthorityUtils.createAuthorityList(role.toString()));
        this.userID = userID;
        this.roleID = role.getId();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return super.getUsername();
    }

    public Long getId() {
        return userID;
    }

    public String getRole() {
        return super.getAuthorities().toArray()[0].toString();
    }

    public int getRoleId() {
        return roleID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
