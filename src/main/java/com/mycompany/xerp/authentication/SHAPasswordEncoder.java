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

import com.mycompany.xerp.util.Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author smantzouratos
 */
public class SHAPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence cs) {
        return cs.toString();
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return Util.createAlgorithm(cs.toString(), "SHA").equals(string);
    }

}
