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
import com.timbercld.ws.system.dao.SystemLanguageDAO;
import com.timbercld.ws.system.entity.SystemLanguageEntity;
import com.timbercld.ws.system.service.SystemLanguageService;
import org.springframework.stereotype.Service;

/**
 * 国际化
 *
 * 
 */
@Service
public class SystemLanguageServiceImpl extends BasicServiceImpl<SystemLanguageDAO, SystemLanguageEntity> implements SystemLanguageService {

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SystemLanguageEntity entity = new SystemLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        SystemLanguageEntity systemLanguageEntity = basicDao.getLanguage(entity);
        //判断是否有数据
        if(null == systemLanguageEntity){
            basicDao.insert(entity);
        }else {
            basicDao.updateLanguage(entity);
        }
    }

    @Override
    public void deleteLanguage(String tableName, Long tableId) {
        basicDao.deleteLanguage(tableName, tableId);
    }
}