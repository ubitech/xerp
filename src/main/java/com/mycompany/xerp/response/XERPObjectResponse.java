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
package com.mycompany.xerp.response;

/**
 *
 * @author smantzouratos
 * @param <R>
 */
public class XERPObjectResponse<R> {

    private String code;
    private String message;
    private R returnobject;

    public XERPObjectResponse() {
    }    
    
    public XERPObjectResponse(String code, String message, R returnobject) {
        this.message = message;
        this.code = code;
        this.returnobject = returnobject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public R getReturnobject() {
        return returnobject;
    }

    public void setReturnobject(R returnobject) {
        this.returnobject = returnobject;
    }
    
        
}
