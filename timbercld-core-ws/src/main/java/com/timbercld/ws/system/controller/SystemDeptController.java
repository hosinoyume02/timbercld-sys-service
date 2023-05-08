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

import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.core.validator.group.UpdateGroup;
import com.timbercld.ws.system.dto.SystemDeptDTO;
import com.timbercld.ws.system.service.SystemDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 * 
 *
 */
@RestController
@RequestMapping("/system/dept")
@Api(tags="部门管理")
public class SystemDeptController {
	@Autowired
	private SystemDeptService systemDeptService;

	@GetMapping("list")
	@ApiOperation("列表")
	@RequiresPermissions("system:dept:list")
	public Result<List<SystemDeptDTO>> list(){
		List<SystemDeptDTO> list = systemDeptService.list(new HashMap<>(1));

		return new Result<List<SystemDeptDTO>>().ok(list);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:dept:info")
	public Result<SystemDeptDTO> get(@PathVariable("id") Long id){
		SystemDeptDTO data = systemDeptService.get(id);

		return new Result<SystemDeptDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("system:dept:save")
	public Result save(@RequestBody SystemDeptDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, SaveGroup.class, DefaultGroup.class);

		systemDeptService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("system:dept:update")
	public Result update(@RequestBody SystemDeptDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		systemDeptService.update(dto);

		return new Result();
	}

	@DeleteMapping("{id}")
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("system:dept:delete")
	public Result delete(@PathVariable("id") Long id){
		//效验数据
		AssertUtils.isNull(id, "id");

		systemDeptService.delete(id);

		return new Result();
	}
	
}