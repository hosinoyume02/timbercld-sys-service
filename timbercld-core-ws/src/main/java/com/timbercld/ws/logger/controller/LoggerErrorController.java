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

package com.timbercld.ws.logger.controller;

import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.utils.ExcelUtils;
import com.timbercld.core.utils.Result;
import com.timbercld.ws.logger.dto.LoggerErrorDTO;
import com.timbercld.ws.logger.excel.LoggerErrorExcel;
import com.timbercld.ws.logger.service.LoggerErrorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 异常日志
 * @author timberbackend
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("system/logger/error")
@Api(tags="异常日志")
public class LoggerErrorController {
    @Autowired
    private LoggerErrorService loggerErrorService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class)
    })
    @RequiresPermissions("system:logger:error")
    public Result<PageData<LoggerErrorDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<LoggerErrorDTO> page = loggerErrorService.page(params);

        return new Result<PageData<LoggerErrorDTO>>().ok(page);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("system:logger:error")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<LoggerErrorDTO> list = loggerErrorService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, LoggerErrorExcel.class);
    }

}