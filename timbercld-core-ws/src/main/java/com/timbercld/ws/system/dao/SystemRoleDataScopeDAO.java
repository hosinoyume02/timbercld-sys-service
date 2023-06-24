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
import com.timbercld.ws.system.entity.SystemRoleDataScopeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色数据权限
 *
 * @author timberbackend
 */
@Mapper
public interface SystemRoleDataScopeDAO extends BasicDao<SystemRoleDataScopeEntity> {

    /**
     * get department id by role id
     * @param roleId
     * @return list
     */
    List<Long> getDeptIdList(Long roleId);

    /**
     * get data scope list by user id
     * @param userId
     * @return list
     */
    List<Long> getDataScopeList(Long userId);

    /**
     * delete role scope data by role ids
     * @param roleIds
     */
    void deleteByRoleIds(Long[] roleIds);
}