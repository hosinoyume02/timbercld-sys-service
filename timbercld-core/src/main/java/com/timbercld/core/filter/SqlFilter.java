/**
 * Copyright (C) 2022-2023 Timber Chain Cloud (TimberCLD). All Rights Reserved.
 *
 * @author TimberCLD
 * @email account@timbercld.com
 * @site https://www.timbercld.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
* Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.timbercld.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * SQL过滤
 *
 */
public class SqlFilter implements Filter {
    private static String strSql="^(.+)\\sand\\s(.+)|(.+)\\sor(.+)\\s$";
    private static  String sqlKeyWord =  "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|and|or|delete|trancate|char|into|substr|ascii|declare|exec|count|into|drop|execute)\\b)";
    private static Pattern sqlPattern = Pattern.compile(strSql, Pattern.CASE_INSENSITIVE);
    private static Pattern sqlKeyWordPattern = Pattern.compile(sqlKeyWord, Pattern.CASE_INSENSITIVE);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if(this.validSQL((HttpServletRequest)request, (HttpServletResponse)response)){
            filterChain.doFilter(request, response);
        }else{
            throw new IOException("illegal request");
        }
    }
    public boolean validSQL(HttpServletRequest request , HttpServletResponse response) {
        Enumeration enumeration=request.getParameterNames();
        String name = "";
        String[] values;
        boolean checkResult=true;
        while(enumeration != null && enumeration.hasMoreElements()){
            name= enumeration.nextElement().toString();
            values=request.getParameterValues(name);
            if(values!=null){
                for(int i=0;i<values.length;i++){

                    if(sqlPattern.matcher(values[i]).find()){
                        checkResult=false;
                        break;
                    }
                    if(sqlKeyWordPattern.matcher(values[i]).find()){
                        checkResult=false;
                        break;
                    }
                }
            }
        }
        return checkResult;
    };

}
