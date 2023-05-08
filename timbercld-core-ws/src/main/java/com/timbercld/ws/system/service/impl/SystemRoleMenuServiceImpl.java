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

import cn.hutool.core.collection.CollUtil;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.ws.system.dao.SystemRoleMenuDAO;
import com.timbercld.ws.system.entity.SystemRoleMenuEntity;
import com.timbercld.ws.system.service.SystemRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与菜单对应关系
 * 
 * 
 */
@Service
public class SystemRoleMenuServiceImpl extends BasicServiceImpl<SystemRoleMenuDAO, SystemRoleMenuEntity> implements SystemRoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		Map<String,Object> map = new HashMap<>();
		map.put("role_id",roleId);
		basicDao.deleteByMap(map);
		if(CollUtil.isEmpty(menuIdList)){
			return ;
		}
		for(Long menuId : menuIdList){
			SystemRoleMenuEntity systemRoleMenuEntity = new SystemRoleMenuEntity();
			systemRoleMenuEntity.setMenuId(menuId);
			systemRoleMenuEntity.setRoleId(roleId);
			insert(systemRoleMenuEntity);
		}
	}

	@Override
	public List<Long> getMenuIdList(Long roleId){
		return basicDao.getMenuIdList(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByRoleIds(Long[] roleIds) {
		basicDao.deleteByRoleIds(roleIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByMenuId(Long menuId) {
		basicDao.deleteByMenuId(menuId);
	}

}