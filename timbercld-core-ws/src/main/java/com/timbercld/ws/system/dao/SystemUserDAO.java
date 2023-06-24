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
import com.timbercld.ws.system.entity.SystemUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * @author timberbackend
 *
 */
@Mapper
public interface SystemUserDAO extends BasicDao<SystemUserEntity> {
	/**
	 * get system user list
	 * @param params
	 * @return list
	 */
	List<SystemUserEntity> getList(Map<String, Object> params);
	/**
	 * get system user by id
	 * @param id
	 * @return systemUserEntity
	 */
	SystemUserEntity getById(Long id);
	/**
	 * get system user by username
	 * @param username
	 * @return systemUserEntity
	 */
	SystemUserEntity getByUsername(String username);
	/**
	 * update password by user id
	 * @param id
	 * @param newPassword
	 * @return int
	 */
	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	/**
	 * get user number by department id
	 * @param deptId
	 * @return int
	 */
	int getCountByDeptId(Long deptId);

}