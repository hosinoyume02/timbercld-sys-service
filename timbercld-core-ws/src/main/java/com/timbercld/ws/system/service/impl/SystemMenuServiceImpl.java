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

import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.core.utils.HttpContextUtils;
import com.timbercld.core.utils.TreeUtils;
import com.timbercld.ws.system.dao.SystemMenuDAO;
import com.timbercld.ws.system.dto.SystemMenuDTO;
import com.timbercld.ws.system.entity.SystemMenuEntity;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import com.timbercld.ws.system.service.SystemLanguageService;
import com.timbercld.ws.system.service.SystemMenuService;
import com.timbercld.ws.system.service.SystemRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemMenuServiceImpl extends BasicServiceImpl<SystemMenuDAO, SystemMenuEntity> implements SystemMenuService {
	@Autowired
	private SystemRoleMenuService systemRoleMenuService;
	@Autowired
	private SystemLanguageService systemLanguageService;

	@Override
	public SystemMenuDTO get(Long id) {
		SystemMenuEntity entity = basicDao.getById(id, HttpContextUtils.getLanguage());
		SystemMenuDTO systemMenuDTO = ConvertUtils.sourceToTarget(entity, SystemMenuDTO.class);
		return systemMenuDTO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SystemMenuDTO dto) {
		SystemMenuEntity entity = ConvertUtils.sourceToTarget(dto, SystemMenuEntity.class);
		insert(entity);
		saveLanguage(entity.getId(), "name", entity.getName());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SystemMenuDTO dto) {
		SystemMenuEntity entity = ConvertUtils.sourceToTarget(dto, SystemMenuEntity.class);
		if(entity.getId().equals(entity.getPid())){
			throw new TimbercldException(ErrorCode.ERR_SUPERIOR_MENU);
		}
		updateById(entity);
		saveLanguage(entity.getId(), "name", entity.getName());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		deleteById(id);
		systemLanguageService.deleteLanguage("system_menu", id);
		systemRoleMenuService.deleteByMenuId(id);
	}

	@Override
	public List<SystemMenuDTO> getAllMenuList(Integer type) {
		List<SystemMenuEntity> menuList = basicDao.getMenuList(type, HttpContextUtils.getLanguage());
		List<SystemMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SystemMenuDTO.class);
		return TreeUtils.build(dtoList, Constant.MENU_ROOT);
	}

	@Override
	public List<SystemMenuDTO> getUserMenuList(LoginUserDTO user, Integer type) {
		List<SystemMenuEntity> menuList;
		if(user.getSuperAdmin() == SuperAdminEnum.YES.value()){
			menuList = basicDao.getMenuList(type, HttpContextUtils.getLanguage());
		}else {
			menuList = basicDao.getUserMenuList(user.getId(), type, HttpContextUtils.getLanguage());
		}
		List<SystemMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SystemMenuDTO.class);
		return TreeUtils.build(dtoList);
	}

	@Override
	public List<SystemMenuDTO> getListPid(Long pid) {
		List<SystemMenuEntity> menuList = basicDao.getListPid(pid);
		return ConvertUtils.sourceToTarget(menuList, SystemMenuDTO.class);
	}

	private void saveLanguage(Long tableId, String fieldName, String fieldValue){
		systemLanguageService.saveOrUpdate("system_menu", tableId, fieldName, fieldValue, HttpContextUtils.getLanguage());
	}

}