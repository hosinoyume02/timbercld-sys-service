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

package com.timbercld.ws.logger.service;

import com.timbercld.core.page.PageData;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.logger.dto.LoggerOperationDTO;
import com.timbercld.ws.logger.entity.LoggerOperationEntity;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 * @author timberbackend
 * 
 * @since 1.0.0
 */
public interface LoggerOperationService extends BasicService<LoggerOperationEntity> {

    /**
     * page list operation logger
     * @param params
     * @return PageData
     */
    PageData<LoggerOperationDTO> page(Map<String, Object> params);

    /**
     * list operation logger
     * @param params
     * @return List
     */
    List<LoggerOperationDTO> list(Map<String, Object> params);
}