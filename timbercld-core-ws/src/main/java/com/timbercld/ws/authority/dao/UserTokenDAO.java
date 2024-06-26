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

package com.timbercld.ws.authority.dao;

import com.timbercld.ws.authority.entity.TokenEntity;
import com.timbercld.core.dao.BasicDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户Token
 * @author timberbackend
 * 
 */
@Mapper
public interface UserTokenDAO extends BasicDao<TokenEntity> {

    /**
     * get entity by token
     * @param token
     * @return tokenEntity
     */
    TokenEntity getByToken(String token);
    /**
     * get entity by token
     * @param userId
     * @return tokenEntity
     */
    TokenEntity getByUserId(Long userId);
    /**
     * update token by userId
     * @param  userId
     * @param token
     */
    void updateToken(@Param("userId") Long userId, @Param("token") String token);
}
