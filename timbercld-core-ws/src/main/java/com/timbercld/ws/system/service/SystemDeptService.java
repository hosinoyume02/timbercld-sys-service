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

import com.timbercld.core.service.BasicService;
import com.timbercld.ws.system.dto.SystemDeptDTO;
import com.timbercld.ws.system.entity.SystemDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author timberbackend
 * 
 */
public interface SystemDeptService extends BasicService<SystemDeptEntity> {
	/**
	 * get system department list
	 * @param params
	 * @return list
	 */
	List<SystemDeptDTO> list(Map<String, Object> params);
	/**
	 * get system department
	 * @param id
	 * @return systemDeptDTO
	 */
	SystemDeptDTO get(Long id);
	/**
	 * save system department
	 * @param dto
	 */
	void save(SystemDeptDTO dto);
	/**
	 * update system department
	 * @param dto
	 */
	void update(SystemDeptDTO dto);
	/**
	 * delete system department
	 * @param id
	 */
	void delete(Long id);

	/**
	 * get sub department id by department id
	 * @param id
	 * @return list
	 */
	List<Long> getSubDeptIdList(Long id);
}