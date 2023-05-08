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

package com.timbercld.ws.system.handler;

import cn.hutool.core.map.MapUtil;
import com.google.gson.Gson;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.ExceptionUtils;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.utils.HttpContextUtils;
import com.timbercld.core.utils.IpUtils;
import com.timbercld.core.utils.Result;
import com.timbercld.ws.logger.entity.LoggerErrorEntity;
import com.timbercld.ws.logger.service.LoggerErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 异常处理器
 *
 * 
 * @since 1.0.0
 */
@RestControllerAdvice
public class TimbercldExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(TimbercldExceptionHandler.class);

	@Autowired
	private LoggerErrorService loggerErrorService;

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(TimbercldException.class)
	public Result handleTimbercldException(TimbercldException ex){
		Result result = new Result();
		result.error(ex.getCode(), ex.getMsg());
		return result;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException ex){
		Result result = new Result();
		result.error(ErrorCode.DB_RECORD_EXISTS);
		return result;
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex){
		logger.error(ex.getMessage(), ex);

		saveLog(ex);

		return new Result().error();
	}


	private void saveLog(Exception ex){
		LoggerErrorEntity loggerErrorEntity = new LoggerErrorEntity();
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		loggerErrorEntity.setIp(IpUtils.getIpAddr(request));
		loggerErrorEntity.setRequestUrl(request.getRequestURI());
		loggerErrorEntity.setRequestMethod(request.getMethod());
		loggerErrorEntity.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		Map<String, String> params = HttpContextUtils.getParameterMap(request);
		if(MapUtil.isNotEmpty(params)){
			loggerErrorEntity.setRequestParams(new Gson().toJson(params));
		}
		loggerErrorEntity.setErrorInfo(ExceptionUtils.catchErrorStackTrace(ex));
		loggerErrorService.insert(loggerErrorEntity);
	}
}