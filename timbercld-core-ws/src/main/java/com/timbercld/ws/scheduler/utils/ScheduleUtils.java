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

import com.timbercld.core.constant.SchedulerStatusEnum;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.ws.scheduler.entity.SchedulerEntity;
import org.quartz.Scheduler;
import org.quartz.*;

/**
 * util for scheduler
 * @author timberbackend
 *
 */
public class ScheduleUtils {
    private final static String PREFIX_JOB_NAME = "TIMBERCLD_TASK_";
    /**
     * job key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    
    /**
     *  get trigger key
     */
    public static TriggerKey getTriggerKey(Long schedulerId) {
        return TriggerKey.triggerKey(PREFIX_JOB_NAME + schedulerId);
    }
    
    /**
     * get job Key
     */
    public static JobKey getJobKey(Long schedulerId) {
        return JobKey.jobKey(PREFIX_JOB_NAME + schedulerId);
    }

    /**
     * get crontab for trigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long schedulerId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(schedulerId));
        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }

    /**
     * create scheduler
     */
    public static void createScheduler(Scheduler scheduler, SchedulerEntity schedulerEntity) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(Job.class).withIdentity(getJobKey(schedulerEntity.getId())).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerEntity.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(schedulerEntity.getId())).withSchedule(scheduleBuilder).build();
            jobDetail.getJobDataMap().put(JOB_PARAM_KEY, schedulerEntity);
            scheduler.scheduleJob(jobDetail, trigger);
            if(schedulerEntity.getStatus() == SchedulerStatusEnum.PAUSE.getValue()){
            	pauseJob(scheduler, schedulerEntity.getId());
            }
        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }
    
    /**
     * update scheduler
     */
    public static void updateScheduler(Scheduler scheduler, SchedulerEntity schedulerEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(schedulerEntity.getId());


            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerEntity.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, schedulerEntity.getId());

            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            trigger.getJobDataMap().put(JOB_PARAM_KEY, schedulerEntity);

            scheduler.rescheduleJob(triggerKey, trigger);

            if(schedulerEntity.getStatus() == SchedulerStatusEnum.PAUSE.getValue()){
            	pauseJob(scheduler, schedulerEntity.getId());
            }

        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }

    /**
     * run scheduler
     */
    public static void run(Scheduler scheduler, SchedulerEntity schedulerEntity) {
        try {
        	//参数
        	JobDataMap dataMap = new JobDataMap();
        	dataMap.put(JOB_PARAM_KEY, schedulerEntity);

            scheduler.triggerJob(getJobKey(schedulerEntity.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }

    /**
     * pause scheduler
     */
    public static void pauseJob(Scheduler scheduler, Long schedulerId) {
        try {
            scheduler.pauseJob(getJobKey(schedulerId));
        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }

    /**
     * resume scheduler
     */
    public static void resumeJob(Scheduler scheduler, Long schedulerId) {
        try {
            scheduler.resumeJob(getJobKey(schedulerId));
        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }

    /**
     * delete scheduler
     */
    public static void deleteScheduler(Scheduler scheduler, Long schedulerId) {
        try {
            scheduler.deleteJob(getJobKey(schedulerId));
        } catch (SchedulerException e) {
            throw new TimbercldException(ErrorCode.ERR_SCHEDULER, e);
        }
    }
}