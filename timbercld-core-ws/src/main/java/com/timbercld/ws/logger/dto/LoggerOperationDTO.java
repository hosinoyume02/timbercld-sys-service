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

package com.timbercld.ws.logger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 *
 * @since 1.0.0
 */
@Data
@ApiModel(value = "操作日志")
@EqualsAndHashCode(callSuper = false)
public class LoggerOperationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "用户操作")
	private String operation;

	@ApiModelProperty(value = "请求URl")
	private String requestUrl;

	@ApiModelProperty(value = "请求方式")
	private String requestMethod;

	@ApiModelProperty(value = "请求参数")
	private String requestParams;

	@ApiModelProperty(value = "请求时长(毫秒)")
	private Integer requestTime;

	@ApiModelProperty(value = "用户代理")
	private String userAgent;

	@ApiModelProperty(value = "操作IP")
	private String ip;

	@ApiModelProperty(value = "状态  0：失败   1：成功")
	private Integer status;

	@ApiModelProperty(value = "用户名")
	private String creatorName;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

}