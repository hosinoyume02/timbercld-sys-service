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

package com.timbercld.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http
 *
 * 
 */
public class HttpContextUtils {
	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if(null == requestAttributes){
			return null;
		}

		return ((ServletRequestAttributes) requestAttributes).getRequest();
	}

	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, String> params = new HashMap<>();
		while (parameters.hasMoreElements()) {
			String parameter = parameters.nextElement();
			String value = request.getParameter(parameter);
			if (StringUtils.isNoneBlank(value)) {
				params.put(parameter, value);
			}
		}

		return params;
	}

	public static String getDomain(){
		String domain = "";
		HttpServletRequest request = getHttpServletRequest();
		try{
			StringBuffer url = request.getRequestURL();
			domain =  url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
			return domain;
		}catch (Exception e){

		}
		return domain;
	}

	public static String getOrigin(){
		HttpServletRequest request = getHttpServletRequest();
		String origin = "";
		try {
			origin =  request.getHeader(HttpHeaders.ORIGIN);
			return origin;
		}catch (Exception e){

		}
		return origin;
	}

	public static String getLanguage() {

		String defaultLanguage = "zh-CN";

		HttpServletRequest request = getHttpServletRequest();
		if(null == request){
			return defaultLanguage;
		}

		defaultLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

		return defaultLanguage;
	}
}