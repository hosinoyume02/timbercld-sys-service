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
import com.timbercld.core.constant.SchedulerStatusEnum;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.scheduler.dao.SchedulerDAO;
import com.timbercld.ws.scheduler.dto.SchedulerDTO;
import com.timbercld.ws.scheduler.entity.SchedulerEntity;
import com.timbercld.ws.scheduler.service.SchedulerService;
import com.timbercld.ws.scheduler.utils.ScheduleUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SchedulerServiceImpl extends BasicServiceImpl<SchedulerDAO, SchedulerEntity> implements SchedulerService {
	@Autowired
	private Scheduler scheduler;

	@Override
	public PageData<SchedulerDTO> page(Map<String, Object> params) {
		IPage<SchedulerEntity> page = basicDao.selectPage(
			getPage(params, Constant.CREATE_DATE, false),
			getWrapper(params)
		);
		return getPageData(page, SchedulerDTO.class);
	}

	@Override
	public SchedulerDTO get(Long id) {
		SchedulerEntity entity = basicDao.selectById(id);

		return ConvertUtils.sourceToTarget(entity, SchedulerDTO.class);
	}

	@Override
	public List<SchedulerEntity> getList(Map<String, Object> params) {
		return basicDao.selectByMap(params);
	}

	private QueryWrapper<SchedulerEntity> getWrapper(Map<String, Object> params){
		QueryWrapper<SchedulerEntity> wrapper = new QueryWrapper<>();
		String beanName = (String) params.get("beanName");
		wrapper.like(StringUtils.isNoneBlank(beanName), "bean_name", beanName);
		return wrapper;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SchedulerDTO dto) {
		SchedulerEntity entity = ConvertUtils.sourceToTarget(dto, SchedulerEntity.class);
		entity.setStatus(SchedulerStatusEnum.NORMAL.getValue());
        insert(entity);
        ScheduleUtils.createScheduler(scheduler, entity);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SchedulerDTO dto) {
		SchedulerEntity entity = ConvertUtils.sourceToTarget(dto, SchedulerEntity.class);
        ScheduleUtils.updateScheduler(scheduler, entity);
        updateById(entity);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
    	for(Long id : ids){
    		ScheduleUtils.deleteScheduler(scheduler, id);
    	}
    	deleteBatchIds(Arrays.asList(ids));
	}

	@Override
    public int updateBatch(Long[] ids, int status){
    	List<SchedulerEntity> list = basicDao.selectBatchIds(Arrays.asList(ids));
		for (SchedulerEntity entity:list
			 ) {
			entity.setStatus(status);
			updateById(entity);
		}
		return list.size();
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] ids) {
    	for(Long id : ids){
    		ScheduleUtils.run(scheduler, this.selectById(id));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] ids) {
        for(Long id : ids){
    		ScheduleUtils.pauseJob(scheduler, id);
    	}
    	updateBatch(ids, SchedulerStatusEnum.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] ids) {
    	for(Long id : ids){
    		ScheduleUtils.resumeJob(scheduler, id);
    	}

    	updateBatch(ids, SchedulerStatusEnum.NORMAL.getValue());
    }
    
}