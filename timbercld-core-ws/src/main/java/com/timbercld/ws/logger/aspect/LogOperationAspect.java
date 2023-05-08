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

package com.timbercld.ws.logger.aspect;

import com.alibaba.fastjson.JSON;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.utils.HttpContextUtils;
import com.timbercld.core.utils.IpUtils;
import com.timbercld.ws.logger.entity.LoggerOperationEntity;
import com.timbercld.ws.logger.enums.OperationStatusEnum;
import com.timbercld.ws.logger.service.LoggerOperationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 操作日志，切面处理类
 *
 * 
 */
@Aspect
@Component
public class LogOperationAspect {
    @Autowired
    private LoggerOperationService loggerOperationService;

    @Pointcut("@annotation(com.timbercld.ws.logger.aspect.LogOperation)")
    public void logPointCut() {

    }
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = new Date().getTime();
        try {
            Object result = point.proceed();
            long time = new Date().getTime() - beginTime;
            saveLog(point, time, OperationStatusEnum.SUCCESS.value());
            return result;
        }catch(Exception exception) {
            long time = new Date().getTime() - beginTime;
            saveLog(point, time, OperationStatusEnum.FAIL.value());
            throw exception;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status)  {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogOperation annotation = method.getAnnotation(LogOperation.class);
        LoggerOperationEntity loggerOperationEntity = new LoggerOperationEntity();
        if(annotation != null){
            loggerOperationEntity.setOperation(annotation.value());
        }else{
            annotation = joinPoint.getTarget().getClass().getAnnotation(LogOperation.class);
            loggerOperationEntity.setOperation(annotation.value() + method.getName() + "()");
        }
        LoginUserDTO user = AuthorityUtils.getUser();
        loggerOperationEntity.setCreatorName(user.getUsername());
        loggerOperationEntity.setStatus(status);
        loggerOperationEntity.setRequestTime(time);
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if(request==null){
            throw new TimbercldException(ErrorCode.NOT_NULL);
        }
        loggerOperationEntity.setIp(IpUtils.getIpAddr(request));
        loggerOperationEntity.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        loggerOperationEntity.setRequestUrl(request.getRequestURI());
        loggerOperationEntity.setRequestMethod(request.getMethod());
        Object[] args = joinPoint.getArgs();
        try{
            String params = JSON.toJSONString(getMethodParameter(method,args));
            loggerOperationEntity.setRequestParams(params);
        }catch (Exception exception){
            throw new TimbercldException(ErrorCode.ERR_JSON_FORMAT);
        }
        loggerOperationService.insert(loggerOperationEntity);
    }
    private Object getMethodParameter(Method method, Object[] args) {
        Map<String, Object> map = new HashMap<>();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
            if ("password".equals(parameterNames[i]) || "file".equals(parameterNames[i])) {
                map.put(parameterNames[i], "受限的支持类型");
            } else {
                map.put(parameterNames[i], args[i]);
            }
        }
        return map;
    }
}