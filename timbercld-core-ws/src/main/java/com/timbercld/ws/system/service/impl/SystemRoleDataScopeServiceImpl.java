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

import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.ws.system.dao.SystemRoleDataScopeDAO;
import com.timbercld.ws.system.entity.SystemRoleDataScopeEntity;
import com.timbercld.ws.system.service.SystemRoleDataScopeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SystemRoleDataScopeServiceImpl extends BasicServiceImpl<SystemRoleDataScopeDAO, SystemRoleDataScopeEntity>
        implements SystemRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
        return basicDao.getDeptIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",roleId);
        basicDao.deleteByMap(map);
        if(deptIdList != null && deptIdList.size() > 0){
            for(Long deptId : deptIdList){
                SystemRoleDataScopeEntity systemRoleDataScopeEntity = new SystemRoleDataScopeEntity();
                systemRoleDataScopeEntity.setDeptId(deptId);
                systemRoleDataScopeEntity.setRoleId(roleId);
                insert(systemRoleDataScopeEntity);
            }
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        basicDao.deleteByRoleIds(roleIds);
    }
}