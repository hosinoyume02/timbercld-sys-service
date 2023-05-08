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

package com.timbercld.ws.system.service;

import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.system.dto.SystemMenuDTO;
import com.timbercld.ws.system.entity.SystemMenuEntity;

import java.util.List;


/**
 * 菜单管理
 * 
 *
 */
public interface SystemMenuService extends BasicService<SystemMenuEntity> {

	SystemMenuDTO get(Long id);

	void save(SystemMenuDTO dto);

	void update(SystemMenuDTO dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<SystemMenuDTO> getAllMenuList(Integer type);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param type 菜单类型
	 */
	List<SystemMenuDTO> getUserMenuList(LoginUserDTO user, Integer type);

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SystemMenuDTO> getListPid(Long pid);
}
