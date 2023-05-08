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

package com.timbercld.ws.scheduler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.scheduler.dao.SchedulerLogDAO;
import com.timbercld.ws.scheduler.dto.SchedulerLogDTO;
import com.timbercld.ws.scheduler.entity.SchedulerLogEntity;
import com.timbercld.ws.scheduler.service.SchedulerLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SchedulerLogServiceImpl extends BasicServiceImpl<SchedulerLogDAO, SchedulerLogEntity> implements SchedulerLogService {

	@Override
	public PageData<SchedulerLogDTO> page(Map<String, Object> params) {
		IPage<SchedulerLogEntity> page = basicDao.selectPage(
			getPage(params, Constant.CREATE_DATE, false),
			getWrapper(params)
		);
		return getPageData(page, SchedulerLogDTO.class);
	}

	private QueryWrapper<SchedulerLogEntity> getWrapper(Map<String, Object> params){
		String schedulerId = (String) params.get("schedulerId");
		QueryWrapper<SchedulerLogEntity> wrapper = new QueryWrapper<>();
		wrapper.eq(StringUtils.isNoneBlank(schedulerId), "scheduler_id", schedulerId);
		return wrapper;
	}

	@Override
	public SchedulerLogDTO get(Long id) {
		SchedulerLogEntity entity = selectById(id);
		return ConvertUtils.sourceToTarget(entity, SchedulerLogDTO.class);
	}
}