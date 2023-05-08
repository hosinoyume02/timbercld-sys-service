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

package com.timbercld.ws.scheduler.controller;

import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.utils.Result;
import com.timbercld.ws.scheduler.dto.SchedulerLogDTO;
import com.timbercld.ws.scheduler.service.SchedulerLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 定时任务日志
 *
 *
 */
@RestController
@RequestMapping("/system/schedulerlog")
@Api(tags="定时任务日志")
public class SchedulerLogController {
	@Autowired
	private SchedulerLogService schedulerLogService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = "schedulerId", value = "schedulerId", paramType = "query", dataTypeClass = String.class)
	})
	@RequiresPermissions("system:schedule:log")
	public Result<PageData<SchedulerLogDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SchedulerLogDTO> page = schedulerLogService.page(params);
		
		return new Result<PageData<SchedulerLogDTO>>().ok(page);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:schedule:log")
	public Result<SchedulerLogDTO> info(@PathVariable("id") Long id){
		SchedulerLogDTO log = schedulerLogService.get(id);
		
		return new Result<SchedulerLogDTO>().ok(log);
	}
}