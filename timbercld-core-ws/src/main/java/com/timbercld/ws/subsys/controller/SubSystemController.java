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

package com.timbercld.ws.subsys.controller;

import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.core.validator.group.UpdateGroup;
import com.timbercld.ws.subsys.dto.SubSystemDTO;
import com.timbercld.ws.subsys.service.SubSystemService;
import com.timbercld.ws.system.service.SystemRoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("sub/system")
@Api(tags="子系统管理")
public class SubSystemController {
	@Autowired
	private SubSystemService subSystemService;
	@Autowired
	private SystemRoleUserService systemRoleUserService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = "subSystemName", value = "子系统名", paramType = "query", dataTypeClass = String.class)
	})
	@RequiresPermissions("system:subsys:page")
	public Result<PageData<SubSystemDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SubSystemDTO> page = subSystemService.page(params);

		return new Result<PageData<SubSystemDTO>>().ok(page);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:subsys:info")
	public Result<SubSystemDTO> get(@PathVariable("id") Long id){
		SubSystemDTO data = subSystemService.get(id);

		//用户角色列表
		List<Long> roleIdList = systemRoleUserService.getRoleIdList(data.getUserId());
		data.setRoleIdList(roleIdList);

		return new Result<SubSystemDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("system:subsys:save")
	public Result save(@RequestBody SubSystemDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, SaveGroup.class, DefaultGroup.class);

		subSystemService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("system:subsys:update")
	public Result update(@RequestBody SubSystemDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		subSystemService.update(dto);

		return new Result();
	}

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("system:subsys:delete")
	public Result delete(@RequestBody Long[] ids){
		//效验数据
		AssertUtils.isArrayEmpty(ids, "id");

		subSystemService.deleteBatchIds(Arrays.asList(ids));

		return new Result();
	}
}