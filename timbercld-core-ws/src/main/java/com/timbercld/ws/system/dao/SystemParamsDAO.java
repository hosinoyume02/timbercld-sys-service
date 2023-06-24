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

package com.timbercld.ws.system.dao;

import com.timbercld.core.dao.BasicDao;
import com.timbercld.ws.system.entity.SystemParamsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数管理
 * @author timberbackend
 * 
 *
 */
@Mapper
public interface SystemParamsDAO extends BasicDao<SystemParamsEntity> {
    /**
     * get value by code
     * @param paramCode
     * @return string
     */
    String getValueByCode(String paramCode);

    /**
     * get param code list
     * @param ids
     * @return list
     */
    List<String> getParamCodeList(Long[] ids);

    /**
     * update value by code
     * @param paramCode
     * @param paramValue
     * @return int
     */
    int updateValueByCode(@Param("paramCode") String paramCode, @Param("paramValue") String paramValue);
}
