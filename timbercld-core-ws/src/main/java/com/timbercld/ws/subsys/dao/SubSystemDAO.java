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

package com.timbercld.ws.subsys.dao;

import com.timbercld.core.dao.BasicDao;
import com.timbercld.ws.subsys.entity.SubSystemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * subSystem
 * @author timberbackend
 *
 */
@Mapper
public interface SubSystemDAO extends BasicDao<SubSystemEntity> {

    /**
     * list subSystem
     * @param params
     * @return list
     */
    List<SubSystemEntity> getList(Map<String, Object> params);
    /**
     * get subSystem by id
     * @param id
     * @return subSystemEntity
     */
    SubSystemEntity getById(Long id);

    /**
     * get subSystem
     * @param subSystemId
     * @return subSystemEntity
     */
    SubSystemEntity getSubSystemId(Long subSystemId);
    /**
     * batch delete subSystem
     * @param ids
     */
    void deleteBatch(Long[] ids);
}