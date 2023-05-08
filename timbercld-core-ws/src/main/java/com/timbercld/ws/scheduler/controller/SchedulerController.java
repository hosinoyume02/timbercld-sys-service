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

import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.core.validator.group.UpdateGroup;
import com.timbercld.ws.scheduler.dto.SchedulerDTO;
import com.timbercld.ws.scheduler.service.SchedulerService;
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
 * 定时任务
 *
 * 
 */
@RestController
@RequestMapping("/system/scheduler")
@Api(tags="定时任务")
public class SchedulerController {
	@Autowired
	private SchedulerService schedulerService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = "beanName", value = "beanName", paramType = "query", dataTypeClass = String.class)
	})
	@RequiresPermissions("system:schedule:page")
	public Result<PageData<SchedulerDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SchedulerDTO> page = schedulerService.page(params);

		return new Result<PageData<SchedulerDTO>>().ok(page);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:schedule:info")
	public Result<SchedulerDTO> info(@PathVariable("id") Long id){
		SchedulerDTO schedule = schedulerService.get(id);
		
		return new Result<SchedulerDTO>().ok(schedule);
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("system:schedule:save")
	public Result save(@RequestBody SchedulerDTO dto){
		ValidatorUtils.validateEntity(dto, SaveGroup.class, DefaultGroup.class);
		
		schedulerService.save(dto);
		
		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("system:schedule:update")
	public Result update(@RequestBody SchedulerDTO dto){
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
				
		schedulerService.update(dto);
		
		return new Result();
	}

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("system:schedule:delete")
	public Result delete(@RequestBody Long[] ids){
		schedulerService.deleteBatch(ids);
		
		return new Result();
	}

	@PutMapping("/run")
	@ApiOperation("立即执行")
	@LogOperation("立即执行")
	@RequiresPermissions("system:schedule:run")
	public Result run(@RequestBody Long[] ids){
		schedulerService.run(ids);
		
		return new Result();
	}

	@PutMapping("/pause")
	@ApiOperation("暂停")
	@LogOperation("暂停")
	@RequiresPermissions("system:schedule:pause")
	public Result pause(@RequestBody Long[] ids){
		schedulerService.pause(ids);
		
		return new Result();
	}

	@PutMapping("/resume")
	@ApiOperation("恢复")
	@LogOperation("恢复")
	@RequiresPermissions("system:schedule:resume")
	public Result resume(@RequestBody Long[] ids){
		schedulerService.resume(ids);
		
		return new Result();
	}

}