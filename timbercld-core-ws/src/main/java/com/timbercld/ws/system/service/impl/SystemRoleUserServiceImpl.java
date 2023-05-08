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

import cn.hutool.core.collection.CollUtil;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.ws.system.dao.SystemRoleUserDAO;
import com.timbercld.ws.system.entity.SystemRoleUserEntity;
import com.timbercld.ws.system.service.SystemRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色用户关系
 *
 *
 * @since 1.0.0
 */
@Service
public class SystemRoleUserServiceImpl extends BasicServiceImpl<SystemRoleUserDAO, SystemRoleUserEntity> implements SystemRoleUserService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        deleteByUserIds(new Long[]{userId});
        if(CollUtil.isEmpty(roleIdList)){
            return ;
        }
        for(Long roleId : roleIdList){
            SystemRoleUserEntity systemRoleUserEntity = new SystemRoleUserEntity();
            systemRoleUserEntity.setUserId(userId);
            systemRoleUserEntity.setRoleId(roleId);
            insert(systemRoleUserEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        basicDao.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        basicDao.deleteByUserIds(userIds);
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {

        return basicDao.getRoleIdList(userId);
    }
}