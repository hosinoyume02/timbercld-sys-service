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

package com.timbercld.ws.subsys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.timbercld.core.entity.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 子系统管理
 *
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sub_system")
public class SubSystemEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 子系统编码
     */
	private Long subSystemId;
    /**
     * 子系统名称
     */
	private String subSystemName;
    /**
     * 状态  0：停用    1：正常
     */
	private Integer status;
    /**
     * 备注
     */
	private String comment;
    /**
     * 登录账号ID
     */
	private Long userId;
    /**
     * 登录账号
     */
	private String username;
    /**
     * 删除标识 0：未删除    1：删除
     */
	private Integer delFlag;
	/**
	 * 系统子系统   0：否   1：是
	 */
	private Integer subSystem;
    /**
     * 更新者
     */


	@TableField(exist = false)
	private String email;
	/**
	 * 手机号
	 */
	@TableField(exist = false)
	private String mobile;
	/**
	 * 真实姓名
	 */
	@TableField(exist = false)
	private String realName;
}