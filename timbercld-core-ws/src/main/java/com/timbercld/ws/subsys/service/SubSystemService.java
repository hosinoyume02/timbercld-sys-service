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

package com.timbercld.ws.subsys.service;

import com.timbercld.core.page.PageData;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.subsys.dto.SubSystemDTO;
import com.timbercld.ws.subsys.entity.SubSystemEntity;

import java.util.Map;


/**
 * subSystem
 * @author timberbackend
 * 
 */
public interface SubSystemService extends BasicService<SubSystemEntity> {
	/**
	 * page list sub system
	 * @param params
	 * @return PageData
	 */
	PageData<SubSystemDTO> page(Map<String, Object> params);
	/**
	 * get sub system
	 * @param id
	 * @return subSystemDTO
	 */
	SubSystemDTO get(Long id);
	/**
	 * save sub system
	 * @param dto
	 */
	void save(SubSystemDTO dto);
	/**
	 * update sub system
	 * @param dto
	 */
	void update(SubSystemDTO dto);
	/**
	 * batch delete sub system
	 * @param ids
	 */
	void delete(Long[] ids);
	/**
	 * get sub system id
	 * @param subSystemId
	 * @return subSystemDTO
	 */
	SubSystemDTO getSubSystemId(Long subSystemId);
}
