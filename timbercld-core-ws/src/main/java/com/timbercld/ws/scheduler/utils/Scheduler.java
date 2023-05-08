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

package com.timbercld.ws.scheduler.utils;

import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ExceptionUtils;
import com.timbercld.core.utils.SpringContextUtils;
import com.timbercld.ws.scheduler.entity.SchedulerEntity;
import com.timbercld.ws.scheduler.entity.SchedulerLogEntity;
import com.timbercld.ws.scheduler.service.SchedulerLogService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * 定时任务
 *
 *
 */
public class Scheduler extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) {
        SchedulerEntity schedulerEntity = (SchedulerEntity) context.getMergedJobDataMap().
				get(ScheduleUtils.JOB_PARAM_KEY);


        SchedulerLogEntity schedulerLogEntity = new SchedulerLogEntity();
		schedulerLogEntity.setSchedulerId(schedulerEntity.getId());
		schedulerLogEntity.setParams(schedulerEntity.getParams());
		schedulerLogEntity.setBeanName(schedulerEntity.getBeanName());
        long startTime = new Date().getTime();
        try {
			Object target = SpringContextUtils.getBean(schedulerEntity.getBeanName());
			Method method = target.getClass().getDeclaredMethod("run", String.class);
			method.invoke(target, schedulerEntity.getParams());
			int times = Integer.parseInt(String.valueOf(new Date().getTime() - startTime));
			schedulerLogEntity.setTimes(times);
			schedulerLogEntity.setStatus(Constant.SUCCESS);
		} catch (Exception e) {
			int times = Integer.parseInt(String.valueOf(new Date().getTime() - startTime));
			schedulerLogEntity.setTimes(times);
			schedulerLogEntity.setStatus(Constant.FAIL);
			schedulerLogEntity.setError(ExceptionUtils.catchErrorStackTrace(e));
		}finally {
			SchedulerLogService schedulerLogService = SpringContextUtils.getBean(SchedulerLogService.class);
			schedulerLogService.insert(schedulerLogEntity);
		}
    }
}