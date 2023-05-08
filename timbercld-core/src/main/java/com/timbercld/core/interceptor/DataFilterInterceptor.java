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

package com.timbercld.core.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.util.Map;



public class DataFilterInterceptor  implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        DataScope dataScope = getDataScope(parameter);
        if(null == dataScope|| StringUtils.isBlank(dataScope.getSqlFilter())){
            return;
        }
        String buildSql = getSelect(boundSql.getSql(), dataScope);
        PluginUtils.mpBoundSql(boundSql).sql(buildSql);
    }

    private DataScope getDataScope(Object parameter){
        if (null == parameter){
            return null;
        }
        DataScope dataScope = null;
        if (parameter instanceof DataScope) {
            dataScope = (DataScope) parameter;
        } else if (parameter instanceof Map) {
            for (Object arg : ((Map) parameter).values()) {
                if (arg instanceof DataScope) {
                    dataScope = (DataScope) arg;
                    break;
                }
            }
        }
        return dataScope;
    }

    private String getSelect(String buildSql, DataScope scope){
        try {
            Select select = (Select) CCJSqlParserUtil.parse(buildSql);
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();

            Expression expression = plainSelect.getWhere();
           StringValue stringValue =  new StringValue(scope.getSqlFilter());
            if(null == expression){
                plainSelect.setWhere(stringValue);
            }else{
                AndExpression andExpression =  new AndExpression(expression, stringValue);
                plainSelect.setWhere(andExpression);
            }
            return String.valueOf(select).replaceAll("'", "");
        }catch (JSQLParserException e){
            return buildSql;
        }
    }
}