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
 * 
 * 
 */
public interface SystemUserService extends BasicService<SystemUserEntity> {

	PageData<SystemUserDTO> page(Map<String, Object> params);

	List<SystemUserDTO> list(Map<String, Object> params);

	SystemUserDTO get(Long id);

	SystemUserDTO getByUsername(String username);

	void save(SystemUserDTO dto);


	void update(SystemUserDTO dto);

	void delete(Long[] ids);

	/**
	 * 修改密码
	 * @param id           用户ID
	 * @param newPassword  新密码
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);
}
