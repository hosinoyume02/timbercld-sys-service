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

package com.timbercld.ws.system.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户管理
 * @author timberbackend
 *
 */
@Data
public class SystemUserExcel {
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "姓名")
    private String realName;
    @Excel(name = "性别", replace = {"男_0", "女_1", "保密_2"})
    private Integer gender;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "手机号")
    private String mobile;
    @Excel(name = "部门名称")
    private String deptName;
    @Excel(name = "状态", replace = {"停用_0", "正常_1"})
    private Integer status;
    @Excel(name = "备注")
    private String comment;
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
