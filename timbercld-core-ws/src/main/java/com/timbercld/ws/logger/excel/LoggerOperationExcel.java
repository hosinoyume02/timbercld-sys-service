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

package com.timbercld.ws.logger.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 * @author timberbackend
 *
 * @since 1.0.0
 */
@Data
public class LoggerOperationExcel {
    @Excel(name = "用户操作")
    private String operation;
    @Excel(name = "请求URI")
    private String requestUrl;
    @Excel(name = "请求方式")
    private String requestMethod;
    @Excel(name = "请求参数")
    private String requestParams;
    @Excel(name = "请求时长(毫秒)")
    private Integer requestTime;
    @Excel(name = "User-Agent")
    private String userAgent;
    @Excel(name = "操作IP")
    private String ip;
    @Excel(name = "状态", replace = {"失败_0", "成功_1"})
    private Integer status;
    @Excel(name = "用户名")
    private String creatorName;
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
