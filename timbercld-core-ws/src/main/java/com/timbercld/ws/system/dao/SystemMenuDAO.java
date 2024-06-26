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

package com.timbercld.ws.system.dao;

import com.timbercld.core.dao.BasicDao;
import com.timbercld.ws.system.entity.SystemMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理
 * @author timerbackend
 * 
 */
@Mapper
public interface SystemMenuDAO extends BasicDao<SystemMenuEntity> {

	/**
	 * get menu by id and language
	 * @param id
	 * @param language
	 * @return systemMenuEntity
	 */
	SystemMenuEntity getById(@Param("id") Long id, @Param("language") String language);

	/**
	 * get menu list
	 * @param type
	 * @param language
	 * @return list
	 */
	List<SystemMenuEntity> getMenuList(@Param("type") Integer type, @Param("language") String language);

	/**
	 * get user menu list
	 * @param userId
	 * @param type
	 * @param language
	 * @return list
	 */
	List<SystemMenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("type") Integer type, @Param("language") String language);

	/**
	 * get user permission list
	 * @param userId
	 * @return list
	 */
	List<String> getUserPermissionsList(Long userId);

	/**
	 * get all permission list
	 * @return list
	 */
	List<String> getPermissionsList();

	/**
	 * get menu list by pid
	 * @param pid
	 * @return list
	 */
	List<SystemMenuEntity> getListPid(Long pid);

}
