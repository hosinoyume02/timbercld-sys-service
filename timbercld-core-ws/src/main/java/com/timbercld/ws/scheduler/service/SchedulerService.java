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

package com.timbercld.ws.scheduler.service;

import com.timbercld.core.page.PageData;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.scheduler.dto.SchedulerDTO;
import com.timbercld.ws.scheduler.entity.SchedulerEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 * @author timberbackend
 *
 */
public interface SchedulerService extends BasicService<SchedulerEntity> {

	/**
	 * page list scheduler
	 * @param params
	 * @return PageData
	 */
	PageData<SchedulerDTO> page(Map<String, Object> params);
	/**
	 * get scheduler
	 * @param id
	 * @return schedulerDTO
	 */
	SchedulerDTO get(Long id);
	/**
	 * get scheduler list
	 * @param params
	 * @return list
	 */
	List<SchedulerEntity> getList(Map<String,Object> params);
	/**
	 * save scheduler
	 * @param dto
	 *
	 */
	void save(SchedulerDTO dto);
	
	/**
	 * update scheduler
	 * @param dto
	 */
	void update(SchedulerDTO dto);
	
	/**
	 * batch delete scheduler
	 * @param ids
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * batch update scheduler status
	 * @param ids
	 * @param status
	 * @return int
	 */
	int updateBatch(Long[] ids, int status);
	
	/**
	 * run scheduler
	 * @param ids
	 *
	 */
	void run(Long[] ids);
	
	/**
	 * pause scheduler
	 * @param ids
	 */
	void pause(Long[] ids);
	
	/**
	 * resume scheduler
	 * @param ids
	 */
	void resume(Long[] ids);
}
