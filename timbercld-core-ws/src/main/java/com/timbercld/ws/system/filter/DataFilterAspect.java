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

package com.timbercld.ws.system.filter;

import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.interceptor.DataScope;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 *
 */
@Aspect
@Component
public class DataFilterAspect {

    @Pointcut(value = "@annotation(com.timbercld.ws.system.filter.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) {
        Object params = point.getArgs()[0];
        if(params instanceof Map){
            LoginUserDTO user = AuthorityUtils.getUser();
            if(user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
                return ;
            }
            try {
                Map map = (Map)params;
                String sqlFilter = getSqlFilter(user, point);
                map.put(Constant.SQL_FILTER, new DataScope(sqlFilter));
            }catch (Exception e){

            }

            return ;
        }

        throw new TimbercldException(ErrorCode.ERR_DATA_SCOPE_PARAMS);
    }

    /**
     * dataFilter过滤数据权限
     */
    private String getSqlFilter(LoginUserDTO user, JoinPoint point) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        DataFilter dataFilter = method.getAnnotation(DataFilter.class);
        String tableAlias = dataFilter.tableAlias();
        if(StringUtils.isNoneBlank(tableAlias)){
            tableAlias = tableAlias +  ".";
        }
        StringBuilder sqlFilter = new StringBuilder();
        String prefix = dataFilter.prefix();
        if(StringUtils.isNoneBlank(prefix)){
            sqlFilter.append(" ").append(prefix);
        }
        sqlFilter.append(" (");

        List<Long> deptIdList = user.getDeptIdList();
        if(deptIdList!=null && deptIdList.size() > 0){
            sqlFilter.append(tableAlias).append(dataFilter.deptId());
            sqlFilter.append(" in(").append(StringUtils.join(deptIdList, ",")).append(")");
        }
        if(deptIdList!=null && deptIdList.size() > 0){
            sqlFilter.append(" or ");
        }
        sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getId());
        sqlFilter.append(")");
        return sqlFilter.toString();
    }
}