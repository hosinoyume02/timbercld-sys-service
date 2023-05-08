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

import com.timbercld.ws.authority.service.PermissionService;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.ws.system.dto.SystemMenuDTO;
import com.timbercld.ws.system.enums.MenuTypeEnum;
import com.timbercld.ws.system.service.SystemMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 * 
 *
 */
@RestController
@RequestMapping("system/menu")
@Api(tags="菜单管理")
public class SystemMenuController {
	@Autowired
	private SystemMenuService systemMenuService;
	@Autowired
	private PermissionService permissionService;

	@GetMapping("nav")
	@ApiOperation("导航")
	public Result<List<SystemMenuDTO>> nav(){
		LoginUserDTO user = AuthorityUtils.getUser();
		List<SystemMenuDTO> list = systemMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());

		return new Result<List<SystemMenuDTO>>().ok(list);
	}

	@GetMapping("permissions")
	@ApiOperation("权限标识")
	public Result<Set<String>> permissions(){
		LoginUserDTO user = AuthorityUtils.getUser();
		Set<String> set = permissionService.getUserPermissions(user);

		return new Result<Set<String>>().ok(set);
	}

	@GetMapping("list")
	@ApiOperation("列表")
	@ApiImplicitParam(name = "type", value = "菜单类型 0：菜单 1：按钮  null：全部", paramType = "query", dataTypeClass = Integer.class)
	@RequiresPermissions("system:menu:list")
	public Result<List<SystemMenuDTO>> list(Integer type){
		List<SystemMenuDTO> list = systemMenuService.getAllMenuList(type);

		return new Result<List<SystemMenuDTO>>().ok(list);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:menu:info")
	public Result<SystemMenuDTO> get(@PathVariable("id") Long id){
		SystemMenuDTO data = systemMenuService.get(id);

		return new Result<SystemMenuDTO>().ok(data);
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("system:menu:save")
	public Result save(@RequestBody SystemMenuDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, DefaultGroup.class);

		systemMenuService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("system:menu:update")
	public Result update(@RequestBody SystemMenuDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, DefaultGroup.class);

		systemMenuService.update(dto);

		return new Result();
	}

	@DeleteMapping("{id}")
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("system:menu:delete")
	public Result delete(@PathVariable("id") Long id){
		//效验数据
		AssertUtils.isNull(id, "id");

		//判断是否有子菜单或按钮
		List<SystemMenuDTO> list = systemMenuService.getListPid(id);
		if(list.size() > 0){
			return new Result().error(ErrorCode.SUB_MENU_EXIST);
		}

		systemMenuService.delete(id);

		return new Result();
	}

	@GetMapping("select")
	@ApiOperation("角色菜单权限")
	@RequiresPermissions("system:menu:select")
	public Result<List<SystemMenuDTO>> select(){
		LoginUserDTO user = AuthorityUtils.getUser();
		List<SystemMenuDTO> list = systemMenuService.getUserMenuList(user, null);

		return new Result<List<SystemMenuDTO>>().ok(list);
	}
}