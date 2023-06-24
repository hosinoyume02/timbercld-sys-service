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

import com.timbercld.core.service.BasicService;
import com.timbercld.ws.system.entity.SystemRoleUserEntity;

import java.util.List;

/**
 * 角色用户关系
 * @author timberbackend
 *
 */
public interface SystemRoleUserService extends BasicService<SystemRoleUserEntity> {

    /**
     * save or update
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * delete role user by role id
     * @param roleIds
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * delete role user by user id
     * @param userIds
     */
    void deleteByUserIds(Long[] userIds);

    /**
     * get role user list by user id
     * @param userId
     * @return list
     */
    List<Long> getRoleIdList(Long userId);
}