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

import com.timbercld.ws.authority.password.PasswordUtils;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.page.PageData;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.core.utils.ExcelUtils;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.core.validator.group.UpdateGroup;
import com.timbercld.ws.system.dto.PasswordDTO;
import com.timbercld.ws.system.dto.SystemUserDTO;
import com.timbercld.ws.system.excel.SystemUserExcel;
import com.timbercld.ws.system.service.SystemRoleUserService;
import com.timbercld.ws.system.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * 
 * 
 */
@RestController
@RequestMapping("system/user")
@Api(tags="用户管理")
public class SystemUserController {
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemRoleUserService systemRoleUserService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class) ,
		@ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataTypeClass = String.class)
	})
	@RequiresPermissions("system:user:page")
	public Result<PageData<SystemUserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SystemUserDTO> page = systemUserService.page(params);

		return new Result<PageData<SystemUserDTO>>().ok(page);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("system:user:info")
	public Result<SystemUserDTO> get(@PathVariable("id") Long id){
		SystemUserDTO data = systemUserService.get(id);

		//用户角色列表
		List<Long> roleIdList = systemRoleUserService.getRoleIdList(id);
		data.setRoleIdList(roleIdList);

		return new Result<SystemUserDTO>().ok(data);
	}

	@GetMapping("info")
	@ApiOperation("登录用户信息")
	public Result<SystemUserDTO> info(){
		SystemUserDTO data = ConvertUtils.sourceToTarget(AuthorityUtils.getUser(), SystemUserDTO.class);
		return new Result<SystemUserDTO>().ok(data);
	}

	@PutMapping("password")
	@ApiOperation("修改密码")
	@LogOperation("修改密码")
	public Result password(@RequestBody PasswordDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto);

		LoginUserDTO user = AuthorityUtils.getUser();

		//原密码不正确
		if(!PasswordUtils.matches(dto.getPassword(), user.getPassword())){
			return new Result().error(ErrorCode.ERR_PASSWORD);
		}

		systemUserService.updatePassword(user.getId(), dto.getNewPassword());

		return new Result();
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("system:user:save")
	public Result save(@RequestBody SystemUserDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, SaveGroup.class, DefaultGroup.class);

		systemUserService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("system:user:update")
	public Result update(@RequestBody SystemUserDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		systemUserService.update(dto);

		return new Result();
	}

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("system:user:delete")
	public Result delete(@RequestBody Long[] ids){
		//效验数据
		AssertUtils.isArrayEmpty(ids, "id");

		systemUserService.deleteBatchIds(Arrays.asList(ids));

		return new Result();
	}

	@GetMapping("export")
	@ApiOperation("导出")
	@LogOperation("导出")
	@RequiresPermissions("system:user:export")
	@ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataTypeClass = String.class)
	public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		List<SystemUserDTO> list = systemUserService.list(params);

		ExcelUtils.exportExcelToTarget(response, null, list, SystemUserExcel.class);
	}
}