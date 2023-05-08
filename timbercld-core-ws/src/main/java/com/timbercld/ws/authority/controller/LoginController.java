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

package com.timbercld.ws.authority.controller;


import com.timbercld.ws.authority.dto.LoginInfoDTO;
import com.timbercld.ws.authority.password.PasswordUtils;
import com.timbercld.ws.authority.service.CaptchaService;
import com.timbercld.ws.authority.service.TokenService;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.utils.IpUtils;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.ws.logger.entity.LoggerLoginEntity;
import com.timbercld.ws.logger.enums.LoginOperationEnum;
import com.timbercld.ws.logger.enums.LoginStatusEnum;
import com.timbercld.ws.logger.service.LoggerLoginService;
import com.timbercld.ws.subsys.dto.SubSystemDTO;
import com.timbercld.ws.subsys.service.SubSystemService;
import com.timbercld.ws.system.dto.SystemUserDTO;
import com.timbercld.ws.system.enums.UserStatusEnum;
import com.timbercld.ws.system.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * 登录
 * 
 * 
 */
@RestController
@Api(tags="登录管理")
public class LoginController {
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private LoggerLoginService loggerLoginService;
	@Autowired
	private SubSystemService subSystemService;



	@GetMapping("captcha")
	@ApiOperation(value = "验证码", produces="application/octet-stream")
	@ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "uuid", required = true)
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);
		BufferedImage image = captchaService.create(uuid);
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		out.close();
	}



	private LoggerLoginEntity wrapperSysLogLoginEntity(HttpServletRequest request) {
		LoggerLoginEntity loggerLoginEntity = new LoggerLoginEntity();
		loggerLoginEntity.setOperation(LoginOperationEnum.LOGIN.value());
		loggerLoginEntity.setCreateDate(new Date());
		loggerLoginEntity.setIp(IpUtils.getIpAddr(request));
		loggerLoginEntity.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		loggerLoginEntity.setIp(IpUtils.getIpAddr(request));
		return loggerLoginEntity;
	}

	private void saveLoginSuccessLog(LoggerLoginEntity loggerLoginEntity, SystemUserDTO user) {
		loggerLoginEntity.setStatus(LoginStatusEnum.SUCCESS.value());
		loggerLoginEntity.setCreatorName(user.getUsername());
		loggerLoginService.insert(loggerLoginEntity);
	}

	@PostMapping("login")
	@ApiOperation(value = "登录")
	public Result login(HttpServletRequest request, @RequestBody LoginInfoDTO login) {
		ValidatorUtils.validateEntity(login);
		boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
		if(!flag){
			return new Result().error(ErrorCode.ERR_CAPTCHA);
		}
		LoggerLoginEntity log = this.wrapperSysLogLoginEntity(request);
		SystemUserDTO user = this.loginValidation(log, login.getUsername());
		if(!PasswordUtils.matches(login.getPassword(), user.getPassword())){
			log.setStatus(LoginStatusEnum.FAIL.value());
			log.setCreatorName(user.getUsername());
			loggerLoginService.insert(log);
			throw new TimbercldException(ErrorCode.ERR_ACCOUNT_PASSWORD);
		}
		this.saveLoginSuccessLog(log, user);
		return tokenService.createToken(user.getId());
	}

	private SystemUserDTO loginValidation(LoggerLoginEntity loggerLoginEntity, String username) {
		SystemUserDTO user = systemUserService.getByUsername(username);
		if(null == user){
			loggerLoginEntity.setStatus(LoginStatusEnum.FAIL.value());
			loggerLoginEntity.setCreatorName(username);
			loggerLoginService.insert(loggerLoginEntity);
			throw new TimbercldException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		if(user.getStatus() == UserStatusEnum.DISABLE.value()){
			loggerLoginEntity.setStatus(LoginStatusEnum.LOCK.value());
			loggerLoginEntity.setCreatorName(user.getUsername());
			loggerLoginService.insert(loggerLoginEntity);
			throw new TimbercldException(ErrorCode.ACCOUNT_DISABLE);
		}
		SubSystemDTO subSystemDTO = subSystemService.getSubSystemId(user.getSubSystemId());
		if(null == subSystemDTO || subSystemDTO.getStatus() == 0){
			throw new TimbercldException(ErrorCode.ACCOUNT_DISABLE);
		}
		return user;
	}

	@PostMapping("logout")
	@ApiOperation(value = "退出")
	public Result logout(HttpServletRequest request) {
		LoginUserDTO user = AuthorityUtils.getUser();
		tokenService.logout(user.getId());
		LoggerLoginEntity log = new LoggerLoginEntity();
		log.setIp(IpUtils.getIpAddr(request));
		log.setStatus(LoginStatusEnum.SUCCESS.value());
		log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		log.setCreatorName(user.getUsername());
		log.setOperation(LoginOperationEnum.LOGOUT.value());
		loggerLoginService.insert(log);
		return new Result();
	}
	
}