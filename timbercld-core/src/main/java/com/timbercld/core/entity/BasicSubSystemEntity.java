/**
 * Copyright (c) 2022 木链云 All rights reserved.
 * <p>
 * https://www.timbercld.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.timbercld.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 子系统基础实体类
 *
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BasicSubSystemEntity extends BasicEntity {
    /**
     * 子系统编码
     */
    @TableField(fill = FieldFill.INSERT)
    private Long subSystemId;
}
