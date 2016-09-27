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

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

/**
 *
 * @author smantzouratos
 */
@Service
public class UserDetailsServiceImpl {//} implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());
//    private final UserRepository userService;

//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userService) {
//
//        this.userService = userService;
//    }

    /**
     * Fetch a user from database on a username
     *
     * @param username The username to match for
     * @return An instance of CurrentUser object
     * @throws UsernameNotFoundException
     */
//    @Override
//    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
//        logger.log(Level.INFO, "Authenticating user with username={0}", username);
//        //Fetch user by username
//        User user = userService.findByUsername(username);
//        //Check if user exists
//        if (null == user) {
//            logger.log(Level.WARNING, "User with username={0} has not been found to the database...", username);
//            throw new UsernameNotFoundException("User with username=" + username + "has not been found to the database...");
//        }
//        //Fetch user role
//        return new CurrentUser(user.getId(), user.getUsername(), user.getPassword(), user.getUserRole(), user.getFirstName(), user.getLastName());
//
//    }
}
