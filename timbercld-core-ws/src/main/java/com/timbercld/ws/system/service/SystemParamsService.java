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

package com.timbercld.ws.system.service;


import com.timbercld.core.page.PageData;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.system.dto.SystemParamsDTO;
import com.timbercld.ws.system.entity.SystemParamsEntity;

import java.util.List;
import java.util.Map;

/**
 * 参数管理
 *
 * @author timberbackend
 */
public interface SystemParamsService extends BasicService<SystemParamsEntity> {
    /**
     * page list for system params
     * @param params
     * @return pageData
     */
    PageData<SystemParamsDTO> page(Map<String, Object> params);
    /**
     * list for system params
     * @param params
     * @return list
     */
    List<SystemParamsDTO> list(Map<String, Object> params);
    /**
     * get system params dto
     * @param id
     * @return systemParamsDTO
     */
    SystemParamsDTO get(Long id);
    /**
     * save system params dto
     * @param dto
     */
    void save(SystemParamsDTO dto);
    /**
     * update system params dto
     * @param dto
     */
    void update(SystemParamsDTO dto);
    /**
     * batch delete system params
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * get value by param code
     * @param paramCode
     * @return string
     */
    String getValue(String paramCode);

    /**
     * get value object by param code
     * @param paramCode
     * @param clazz
     * @return T
     */
    <T> T getValueObject(String paramCode, Class<T> clazz);

    /**
     * update value by param code
     * @param paramCode
     * @param paramValue
     * @return int
     */
    int updateValueByCode(String paramCode, String paramValue);
}
