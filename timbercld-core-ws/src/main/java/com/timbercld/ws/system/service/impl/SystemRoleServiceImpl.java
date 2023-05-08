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

package com.timbercld.ws.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.system.dao.SystemRoleDAO;
import com.timbercld.ws.system.dto.SystemRoleDTO;
import com.timbercld.ws.system.entity.SystemRoleEntity;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import com.timbercld.ws.system.enums.SuperSubSystemEnum;
import com.timbercld.ws.system.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色
 * 
 * 
 */
@Service
public class SystemRoleServiceImpl extends BasicServiceImpl<SystemRoleDAO, SystemRoleEntity> implements SystemRoleService {
	@Autowired
	private SystemRoleMenuService systemRoleMenuService;
	@Autowired
	private SystemRoleDataScopeService systemRoleDataScopeService;
	@Autowired
	private SystemRoleUserService systemRoleUserService;
	@Autowired
	private SystemDeptService systemDeptService;

	@Override
	public PageData<SystemRoleDTO> page(Map<String, Object> params) {
		IPage<SystemRoleEntity> page = basicDao.selectPage(
			getPage(params, Constant.CREATE_DATE, false),
			getWrapper(params)
		);

		return getPageData(page, SystemRoleDTO.class);
	}

	@Override
	public List<SystemRoleDTO> list(Map<String, Object> params) {
		List<SystemRoleEntity> entityList = basicDao.selectList(getWrapper(params));

		return ConvertUtils.sourceToTarget(entityList, SystemRoleDTO.class);
	}

	private QueryWrapper<SystemRoleEntity> getWrapper(Map<String, Object> params){
		String name = (String)params.get("name");

		QueryWrapper<SystemRoleEntity> wrapper = new QueryWrapper<>();
		wrapper.like(StringUtils.isNoneBlank(name), "name", name);
		wrapper.eq(Constant.SUB_SYSTEM_ID, AuthorityUtils.getSubSystemId());

		//普通管理员，只能查询所属部门及子部门的数据
		LoginUserDTO user = AuthorityUtils.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value() &&
				user.getSuperSubSystem() == SuperSubSystemEnum.NO.value()) {
			List<Long> deptIdList = systemDeptService.getSubDeptIdList(user.getDeptId());
			wrapper.in(deptIdList != null, "dept_id", deptIdList);
		}

		return wrapper;
	}

	@Override
	public SystemRoleDTO get(Long id) {
		SystemRoleEntity entity = basicDao.selectById(id);

		return ConvertUtils.sourceToTarget(entity, SystemRoleDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SystemRoleDTO dto) {
		SystemRoleEntity entity = ConvertUtils.sourceToTarget(dto, SystemRoleEntity.class);

		//保存角色
		insert(entity);

		//保存角色菜单关系
		systemRoleMenuService.saveOrUpdate(entity.getId(), dto.getMenuIdList());

		//保存角色数据权限关系
		systemRoleDataScopeService.saveOrUpdate(entity.getId(), dto.getDeptIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SystemRoleDTO dto) {
		SystemRoleEntity entity = ConvertUtils.sourceToTarget(dto, SystemRoleEntity.class);

		//更新角色
		updateById(entity);

		//更新角色菜单关系
		systemRoleMenuService.saveOrUpdate(entity.getId(), dto.getMenuIdList());

		//更新角色数据权限关系
		systemRoleDataScopeService.saveOrUpdate(entity.getId(), dto.getDeptIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long[] ids) {
		//删除角色
		basicDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		systemRoleUserService.deleteByRoleIds(ids);

		//删除角色菜单关系
		systemRoleMenuService.deleteByRoleIds(ids);

		//删除角色数据权限关系
		systemRoleDataScopeService.deleteByRoleIds(ids);
	}

}