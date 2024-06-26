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

import com.timbercld.core.page.PageData;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.system.dto.SystemUserDTO;
import com.timbercld.ws.system.entity.SystemUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * @author timberbackend
 * 
 */
public interface SystemUserService extends BasicService<SystemUserEntity> {
	/**
	 * page list system user
	 * @param params
	 * @return pageData
	 */
	PageData<SystemUserDTO> page(Map<String, Object> params);
	/**
	 * list system user
	 * @param params
	 * @return list
	 */
	List<SystemUserDTO> list(Map<String, Object> params);
	/**
	 * get system user
	 * @param id
	 * @return systemUserDTO
	 */
	SystemUserDTO get(Long id);
	/**
	 * get system user
	 * @param username
	 * @return systemUserDTO
	 */
	SystemUserDTO getByUsername(String username);
	/**
	 * save system user
	 * @param dto
	 */
	void save(SystemUserDTO dto);

	/**
	 * update system user
	 * @param dto
	 */
	void update(SystemUserDTO dto);
	/**
	 * batch delete system user
	 * @param ids
	 */
	void delete(Long[] ids);

	/**
	 * update password by user id
	 * @param id
	 * @param newPassword
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * get user number by department id
	 * @param deptId
	 * @return int
	 */
	int getCountByDeptId(Long deptId);
}
