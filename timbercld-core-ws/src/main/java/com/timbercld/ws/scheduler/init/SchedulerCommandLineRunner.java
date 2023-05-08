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

package com.timbercld.ws.scheduler.init;

import com.timbercld.ws.scheduler.entity.SchedulerEntity;
import com.timbercld.ws.scheduler.service.SchedulerService;
import com.timbercld.ws.scheduler.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化定时任务数据
 *
 *
 */
@Component
public class SchedulerCommandLineRunner implements CommandLineRunner {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void run(String... args) {
        List<SchedulerEntity> schedulerEntities = schedulerService.getList(null);
        for(SchedulerEntity schedulerEntity : schedulerEntities){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, schedulerEntity.getId());
            if(null == cronTrigger ) {
                ScheduleUtils.createScheduler(scheduler, schedulerEntity);
            }else {
                ScheduleUtils.updateScheduler(scheduler, schedulerEntity);
            }
        }
    }
}