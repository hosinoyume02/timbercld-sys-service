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
import com.timbercld.ws.logger.dto.LoggerLoginDTO;
import com.timbercld.ws.logger.entity.LoggerLoginEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 *
 * @since 1.0.0
 */
public interface LoggerLoginService extends BasicService<LoggerLoginEntity> {

    PageData<LoggerLoginDTO> page(Map<String, Object> params);

    List<LoggerLoginDTO> list(Map<String, Object> params);

}