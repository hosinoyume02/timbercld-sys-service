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

package com.timbercld.ws.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.system.dao.SystemParamsDAO;
import com.timbercld.ws.system.dto.SystemParamsDTO;
import com.timbercld.ws.system.entity.SystemParamsEntity;
import com.timbercld.ws.system.service.SystemParamsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class SystemParamsServiceImpl extends BasicServiceImpl<SystemParamsDAO, SystemParamsEntity> implements SystemParamsService {
    @Override
    public PageData<SystemParamsDTO> page(Map<String, Object> params) {
        IPage<SystemParamsEntity> page = basicDao.selectPage(
            getPage(params, Constant.UPDATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SystemParamsDTO.class);
    }
    @Override
    public List<SystemParamsDTO> list(Map<String, Object> params) {
        List<SystemParamsEntity> entityList = basicDao.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(entityList, SystemParamsDTO.class);
    }

    private QueryWrapper<SystemParamsEntity> getWrapper(Map<String, Object> params){
        String paramCode = (String) params.get("paramCode");
        QueryWrapper<SystemParamsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("param_type", 1);
        wrapper.like(StringUtils.isNoneBlank(paramCode), "param_code", paramCode);
        return wrapper;
    }

    @Override
    public SystemParamsDTO get(Long id) {
        SystemParamsEntity entity = selectById(id);
        return ConvertUtils.sourceToTarget(entity, SystemParamsDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SystemParamsDTO dto) {
        SystemParamsEntity entity = ConvertUtils.sourceToTarget(dto, SystemParamsEntity.class);
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SystemParamsDTO dto) {
        SystemParamsEntity entity = ConvertUtils.sourceToTarget(dto, SystemParamsEntity.class);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除Redis数据
        List<String> paramCodeList = basicDao.getParamCodeList(ids);
        String[] paramCodes = paramCodeList.toArray(new String[paramCodeList.size()]);


        //逻辑删除
        deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String paramCode) {
        String  paramValue = basicDao.getValueByCode(paramCode);
        return paramValue;
    }

    @Override
    public <T> T getValueObject(String paramCode, Class<T> clazz) {
        String paramValue = getValue(paramCode);
        if(StringUtils.isNoneBlank(paramValue)){
            return JSON.parseObject(paramValue, clazz);
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new TimbercldException(ErrorCode.ERR_PARAMS_GET);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateValueByCode(String paramCode, String paramValue) {
        int count = basicDao.updateValueByCode(paramCode, paramValue);
        return count;
    }

}