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

package com.timbercld.ws.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.core.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 *
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户管理")
public class SystemUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@Null(message="{id.null}", groups = SaveGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank(message="{systemuser.username.require}", groups = DefaultGroup.class)
	private String username;

	@ApiModelProperty(value = "密码")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message="{systemuser.password.require}", groups = SaveGroup.class)
	private String password;

	@ApiModelProperty(value = "姓名", required = true)
	@NotBlank(message="{systemuser.realname.require}", groups = DefaultGroup.class)
	private String realName;

	@ApiModelProperty(value = "头像")
	private String headUrl;

	@ApiModelProperty(value = "性别   0：男   1：女    2：保密", required = true)
	@Range(min=0, max=2, message = "{systemuser.gender.range}", groups = DefaultGroup.class)
	private Integer gender;

	@ApiModelProperty(value = "邮箱", required = true)
	@NotBlank(message="{systemuser.email.require}", groups = DefaultGroup.class)
	@Email(message="{systemuser.email.error}", groups = DefaultGroup.class)
	private String email;

	@ApiModelProperty(value = "手机号", required = true)
	@NotBlank(message="{systemuser.mobile.require}", groups = DefaultGroup.class)
	private String mobile;

	@ApiModelProperty(value = "部门ID", required = true)
	@NotNull(message="{systemuser.deptId.require}", groups = DefaultGroup.class)
	private Long deptId;

	@ApiModelProperty(value = "状态  0：停用    1：正常", required = true)
	@Range(min=0, max=1, message = "{systemuser.status.range}", groups = DefaultGroup.class)
	private Integer status;

	@ApiModelProperty(value = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "超级管理员   0：否   1：是")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer superAdmin;

	@ApiModelProperty(value = "超级子系统   0：否   1：是")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer superSubSystem;

	@ApiModelProperty(value = "子系统Id")
	private Long subSystemId;

	@ApiModelProperty(value = "角色ID列表")
	private List<Long> roleIdList;

	@ApiModelProperty(value = "部门名称")
	private String deptName;

}