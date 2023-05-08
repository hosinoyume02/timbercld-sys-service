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

package com.timbercld.ws.system.controller;

import com.sun.management.OperatingSystemMXBean;
import com.timbercld.core.utils.Result;
import com.timbercld.ws.system.dto.SystemDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 系统接口
 * 
 * 
 */
@RestController
@Api(tags="系统接口")
public class SystemController {

	@GetMapping("system/info")
	@ApiOperation("系统信息")
	public Result<SystemDTO> info(){
		OperatingSystemMXBean osmx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

		SystemDTO dto = new SystemDTO();
		dto.setJavaTotalMemory(Runtime.getRuntime().totalMemory()/1024/1024);
		dto.setJavaFreeMemory(Runtime.getRuntime().freeMemory()/1024/1024);
		dto.setJavaMaxMemory(Runtime.getRuntime().maxMemory()/1024/1024);
		dto.setUserName(System.getProperty("user.name"));
		dto.setSysTime(new Date().getTime());
		dto.setOsName(System.getProperty("os.name"));
		dto.setOsArch(System.getProperty("os.arch"));
		dto.setSystemCpuLoad(BigDecimal.valueOf(osmx.getSystemCpuLoad()*100).setScale(2, RoundingMode.HALF_UP));
		dto.setUserTimezone(System.getProperty("user.timezone"));
		dto.setOsVersion(System.getProperty("os.version"));
		dto.setUserLanguage(System.getProperty("user.language"));
		dto.setUserDir(System.getProperty("user.dir"));
		dto.setTotalPhysical(osmx.getTotalPhysicalMemorySize()/1024/1024);
		dto.setFreePhysical(osmx.getFreePhysicalMemorySize()/1024/1024);
		dto.setMemoryRate(BigDecimal.valueOf((1-osmx.getFreePhysicalMemorySize()*1.0/osmx.getTotalPhysicalMemorySize())*100).setScale(4, RoundingMode.HALF_UP));
		dto.setProcessors(osmx.getAvailableProcessors());
		dto.setJvmName(System.getProperty("java.vm.name"));
		dto.setJavaVersion(System.getProperty("java.version"));
		dto.setJavaHome(System.getProperty("java.home"));


		return new Result<SystemDTO>().ok(dto);
	}

}