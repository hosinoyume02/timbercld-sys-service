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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 参数管理
 *
 * 
 * @author timberbackend
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "参数管理")
public class SystemParamsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Null(message="{id.null}", groups = SaveGroup.class)
    @NotNull(message="{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "参数编码")
    @NotBlank(message="{systemparams.paramcode.require}", groups = DefaultGroup.class)
    private String paramCode;

    @ApiModelProperty(value = "参数值")
    @NotBlank(message="{systemparams.paramvalue.require}", groups = DefaultGroup.class)
    private String paramValue;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateDate;

}
