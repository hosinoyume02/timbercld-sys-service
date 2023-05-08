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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.ws.authority.password.PasswordUtils;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.system.dao.SystemUserDAO;
import com.timbercld.ws.system.dto.SystemUserDTO;
import com.timbercld.ws.system.entity.SystemUserEntity;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import com.timbercld.ws.system.enums.SuperSubSystemEnum;
import com.timbercld.ws.system.service.SystemRoleUserService;
import com.timbercld.ws.system.service.SystemDeptService;
import com.timbercld.ws.system.service.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 *
 */
@Service
public class SystemUserServiceImpl extends BasicServiceImpl<SystemUserDAO, SystemUserEntity> implements SystemUserService {
	@Autowired
	private SystemRoleUserService systemRoleUserService;
	@Autowired
	@Lazy
	private SystemDeptService systemDeptService;

	@Override
	public PageData<SystemUserDTO> page(Map<String, Object> params) {
		//转换成like
		paramsToLike(params, "username");

		//分页
		IPage<SystemUserEntity> page = getPage(params, Constant.UPDATE_DATE, false);

		//查询
		List<SystemUserEntity> list = basicDao.getList(getQueryParams(params));

		return getPageData(list, page.getTotal(), SystemUserDTO.class);
	}

	@Override
	public List<SystemUserDTO> list(Map<String, Object> params) {
		List<SystemUserEntity> list = basicDao.getList(getQueryParams(params));

		return ConvertUtils.sourceToTarget(list, SystemUserDTO.class);
	}

	private Map<String, Object> getQueryParams(Map<String, Object> params){
		LoginUserDTO user = AuthorityUtils.getUser();
		//超级管理员没有权限限制
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()
				&& user.getSuperSubSystem() == SuperSubSystemEnum.NO.value()) {
			params.put("deptIdList", systemDeptService.getSubDeptIdList(user.getDeptId()));
		}

		//子系统
		params.put(Constant.SUB_SYSTEM_ID, user.getSubSystemId());

		return params;
	}

	@Override
	public SystemUserDTO get(Long id) {
		SystemUserEntity entity = basicDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SystemUserDTO.class);
	}

	@Override
	public SystemUserDTO getByUsername(String username) {
		SystemUserEntity entity = basicDao.getByUsername(username);
		return ConvertUtils.sourceToTarget(entity, SystemUserDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SystemUserDTO dto) {
		SystemUserEntity entity = ConvertUtils.sourceToTarget(dto, SystemUserEntity.class);
		entity.setSubSystemId(AuthorityUtils.getSubSystemId());

		this.saveEntity(dto, entity);
	}



	private void saveEntity(SystemUserDTO dto, SystemUserEntity entity) {
		//密码加密
		String password = PasswordUtils.encode(entity.getPassword());
		entity.setPassword(password);

		//保存用户
		entity.setSuperSubSystem(SuperSubSystemEnum.NO.value());
		entity.setSuperAdmin(SuperAdminEnum.NO.value());
		insert(entity);

		//保存角色用户关系
		systemRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SystemUserDTO dto) {
		SystemUserEntity entity = ConvertUtils.sourceToTarget(dto, SystemUserEntity.class);

		//密码加密
		if(StringUtils.isBlank(dto.getPassword())){
			entity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(entity.getPassword());
			entity.setPassword(password);
		}

		//更新用户
		updateById(entity);

		//更新角色用户关系
		systemRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	public void delete(Long[] ids) {
		//删除用户
		basicDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		systemRoleUserService.deleteByUserIds(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(Long id, String newPassword) {
		newPassword = PasswordUtils.encode(newPassword);

		basicDao.updatePassword(id, newPassword);
	}

	@Override
	public int getCountByDeptId(Long deptId) {
		return basicDao.getCountByDeptId(deptId);
	}
}
