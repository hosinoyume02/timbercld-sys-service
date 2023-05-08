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

package com.timbercld.ws.authority.service.impl;

import com.timbercld.ws.authority.dao.UserTokenDAO;
import com.timbercld.ws.authority.entity.TokenEntity;
import com.timbercld.ws.authority.service.PermissionService;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.ws.system.dao.SystemMenuDAO;
import com.timbercld.ws.system.dao.SystemRoleDataScopeDAO;
import com.timbercld.ws.system.dao.SystemUserDAO;
import com.timbercld.ws.system.entity.SystemUserEntity;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private SystemMenuDAO systemMenuDao;
    @Resource
    private SystemUserDAO systemUserDao;
    @Resource
    private UserTokenDAO userTokenDao;
    @Resource
    private SystemRoleDataScopeDAO systemRoleDataScopeDao;

    @Override
    public Set<String> getUserPermissions(LoginUserDTO user) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if(user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = systemMenuDao.getPermissionsList();
        }else{
            permissionsList = systemMenuDao.getUserPermissionsList(user.getId());
        }

        Set<String> permsSet = new HashSet<>();
        for(String permissions : permissionsList){
            if(!StringUtils.isBlank(permissions)){
                String[] strings = permissions.trim().split(",");
                Collections.addAll(permsSet, strings);
            }
        }

        return permsSet;
    }

    @Override
    public TokenEntity getByToken(String token) {
        return userTokenDao.getByToken(token);
    }

    @Override
    public SystemUserEntity getUser(Long userId) {
        return systemUserDao.selectById(userId);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return systemRoleDataScopeDao.getDataScopeList(userId);
    }
}