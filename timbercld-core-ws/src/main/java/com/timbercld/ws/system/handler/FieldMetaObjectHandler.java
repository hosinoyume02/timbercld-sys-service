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

package com.timbercld.ws.system.handler;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.timbercld.ws.authority.utils.AuthorityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * 公共字段，自动填充值
 *
 *
 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_DATE = "createDate";
    private final static String CREATOR_ID = "creatorId";
    private final static String UPDATE_DATE = "updateDate";
    private final static String UPDATER_ID = "updaterId";
    private final static String USER_ID = "userId";
    private final static String DEPT_ID = "deptId";
    private final static String SUBSYSTEM_ID = "subSystemId";

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject,USER_ID, Long.class, AuthorityUtils.getUserId());
        //创建者
        setFieldValByName(CREATOR_ID, AuthorityUtils.getUserId(), metaObject);
        //创建时间
        setFieldValByName(CREATE_DATE, new Date(), metaObject);
        //更新者
        setFieldValByName(UPDATER_ID, AuthorityUtils.getUserId(), metaObject);
        //更新时间
        setFieldValByName(UPDATE_DATE, new Date(), metaObject);
        //创建者所属部门
        strictInsertFill(metaObject , DEPT_ID, Long.class, AuthorityUtils.getDeptId());
        //子系统编码
        strictInsertFill(metaObject, SUBSYSTEM_ID,Long.class , AuthorityUtils.getSubSystemId());


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新者
        setFieldValByName(UPDATER_ID, AuthorityUtils.getUserId(), metaObject);
        //更新时间
        setFieldValByName(UPDATE_DATE, new Date(), metaObject);
    }


}
