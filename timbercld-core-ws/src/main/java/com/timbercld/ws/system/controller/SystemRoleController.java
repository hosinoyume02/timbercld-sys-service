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
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.core.validator.group.UpdateGroup;
import com.timbercld.ws.system.dto.SystemRoleDTO;
import com.timbercld.ws.system.service.SystemRoleDataScopeService;
import com.timbercld.ws.system.service.SystemRoleMenuService;
import com.timbercld.ws.system.service.SystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 *
 */
@RestController
@RequestMapping("system/role")
@Api(tags="角色管理")
public class SystemRoleController {
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private SystemRoleMenuService systemRoleMenuService;
	@Autowired
	private SystemRoleDataScopeService systemRoleDataScopeService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = "name", value = "角色名", paramType = "query", dataTypeClass = String.class)
	})
	@RequiresPermissions("system:role:page")
	public Result<PageData<SystemRoleDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SystemRoleDTO> page = systemRoleService.page(params);

		return new Result<PageData<SystemRoleDTO>>().ok(page);
	}

	@GetMapping("list")
	@ApiOperation("列表")
	@RequiresPermissions("system:role:list")
	public Result<List<SystemRoleDTO>> list(){
		List<SystemRoleDTO> data = systemRoleService.list(new HashMap<>(1));

		return new Result<List<SystemRoleDTO>>().ok(data);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:role:info")
	public Result<SystemRoleDTO> get(@PathVariable("id") Long id){
		SystemRoleDTO data = systemRoleService.get(id);

		//查询角色对应的菜单
		List<Long> menuIdList = systemRoleMenuService.getMenuIdList(id);
		data.setMenuIdList(menuIdList);

		//查询角色对应的数据权限
		List<Long> deptIdList = systemRoleDataScopeService.getDeptIdList(id);
		data.setDeptIdList(deptIdList);

		return new Result<SystemRoleDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("system:role:save")
	public Result save(@RequestBody SystemRoleDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, SaveGroup.class, DefaultGroup.class);

		systemRoleService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("system:role:update")
	public Result update(@RequestBody SystemRoleDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		systemRoleService.update(dto);

		return new Result();
	}

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("system:role:delete")
	public Result delete(@RequestBody Long[] ids){
		//效验数据
		AssertUtils.isArrayEmpty(ids, "id");

		systemRoleService.delete(ids);

		return new Result();
	}
}