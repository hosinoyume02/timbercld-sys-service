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
import com.timbercld.ws.logger.dao.LoggerErrorDAO;
import com.timbercld.ws.logger.dto.LoggerErrorDTO;
import com.timbercld.ws.logger.entity.LoggerErrorEntity;
import com.timbercld.ws.logger.service.LoggerErrorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 *
 * @since 1.0.0
 */
@Service
public class LoggerErrorServiceImpl extends BasicServiceImpl<LoggerErrorDAO, LoggerErrorEntity> implements LoggerErrorService {

    @Override
    public PageData<LoggerErrorDTO> page(Map<String, Object> params) {
        IPage<LoggerErrorEntity> page = basicDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );
        return getPageData(page, LoggerErrorDTO.class);
    }

    @Override
    public List<LoggerErrorDTO> list(Map<String, Object> params) {
        List<LoggerErrorEntity> entityList = basicDao.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(entityList, LoggerErrorDTO.class);
    }

    private QueryWrapper<LoggerErrorEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<LoggerErrorEntity> wrapper = new QueryWrapper<>();
        String requestUrl = (String) params.get("requestUrl");
        String requestParams = (String) params.get("requestParams");
        wrapper.like(StringUtils.isNoneBlank(requestUrl),"request_url",requestUrl);
        wrapper.like(StringUtils.isNoneBlank(requestParams),"request_params",requestParams);
        return wrapper;
    }

}