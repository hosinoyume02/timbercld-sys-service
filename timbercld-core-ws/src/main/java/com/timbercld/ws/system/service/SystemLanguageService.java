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
import com.timbercld.ws.system.entity.SystemLanguageEntity;


/**
 * 国际化
 * @author timberbackend
 * 
 */
public interface SystemLanguageService extends BasicService<SystemLanguageEntity> {

    /**
     * save or update system language
     * @param tableName
     * @param tableId
     * @param fieldName
     * @param fieldValue
     * @param language
     */
    void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language);

    /**
     * delete system language by table name and table id
     * @param tableName
     * @param tableId
     */
    void deleteLanguage(String tableName, Long tableId);
}

