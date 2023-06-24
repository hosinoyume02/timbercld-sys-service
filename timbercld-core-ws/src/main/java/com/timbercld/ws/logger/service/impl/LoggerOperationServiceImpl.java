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

package com.timbercld.ws.logger.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.logger.dao.LoggerOperationDAO;
import com.timbercld.ws.logger.dto.LoggerOperationDTO;
import com.timbercld.ws.logger.entity.LoggerOperationEntity;
import com.timbercld.ws.logger.service.LoggerOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 * @author timberbackend
 *
 * @since 1.0.0
 */
@Service
public class LoggerOperationServiceImpl extends BasicServiceImpl<LoggerOperationDAO, LoggerOperationEntity> implements LoggerOperationService {

    @Override
    public PageData<LoggerOperationDTO> page(Map<String, Object> params) {
        IPage<LoggerOperationEntity> page = basicDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, LoggerOperationDTO.class);
    }

    @Override
    public List<LoggerOperationDTO> list(Map<String, Object> params) {
        List<LoggerOperationEntity> entityList = basicDao.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(entityList, LoggerOperationDTO.class);
    }

    private QueryWrapper<LoggerOperationEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<LoggerOperationEntity> wrapper = new QueryWrapper<>();
        String status =(String) params.get("status");
        String requestUrl = (String) params.get("requestUrl");
        String requestParams = (String) params.get("requestParams");
        wrapper.like(StringUtils.isNoneBlank(requestUrl),"request_uri",requestUrl);
        wrapper.eq(StringUtils.isNoneBlank(status), "status", status);
        wrapper.like(StringUtils.isNoneBlank(requestParams),"request_params",requestParams);
        return wrapper;
    }



}