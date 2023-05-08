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
 *
 *
 */
public interface SchedulerService extends BasicService<SchedulerEntity> {

	PageData<SchedulerDTO> page(Map<String, Object> params);

	SchedulerDTO get(Long id);
	List<SchedulerEntity> getList(Map<String,Object> params);
	/**
	 * 保存定时任务
	 */
	void save(SchedulerDTO dto);
	
	/**
	 * 更新定时任务
	 */
	void update(SchedulerDTO dto);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] ids, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] ids);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] ids);
	
	/**
	 * 恢复运行
	 */
	void resume(Long[] ids);
}
