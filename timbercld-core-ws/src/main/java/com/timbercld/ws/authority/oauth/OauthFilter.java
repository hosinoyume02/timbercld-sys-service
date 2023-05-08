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

package com.timbercld.ws.authority.oauth;

import com.google.gson.Gson;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.utils.HttpContextUtils;
import com.timbercld.core.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class OauthFilter extends AuthenticatingFilter {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String token = getRequestToken((HttpServletRequest) request);

        if(StringUtils.isBlank(token)){
            throw new TimbercldException(ErrorCode.TOKEN_NOT_EMPTY);
        }

        return new OauthToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setContentType("application/json;charset=utf-8");
            String json = new Gson().toJson(new Result().error(ErrorCode.UNAUTHORIZED));
            httpResponse.getWriter().print(json);
            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        httpResponse.setContentType("application/json;charset=utf-8");

        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result result = new Result().error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());
            String json = new Gson().toJson(result);
            httpResponse.getWriter().print(json);
        } catch (IOException ioException) {
            logger.error(ioException.toString());
        }
        return false;
    }


    private String getRequestToken(HttpServletRequest request){
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if(!StringUtils.isNoneBlank(token)){
            token = request.getParameter(Constant.TOKEN_HEADER);
        }
        return token;
    }

}