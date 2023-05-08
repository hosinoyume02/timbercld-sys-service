/**
 * Copyright (c) 2022 木链云 All rights reserved.
 * <p>
 * https://www.timbercld.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.timbercld.core.service;

import com.timbercld.core.page.PageData;

import java.util.List;
import java.util.Map;

/**
 *  CRUD基础服务接口
 *
 *
 */
public interface CrudService<T, D> extends BasicService<T> {

    PageData<D> page(Map<String, Object> params);

    List<D> list(Map<String, Object> params);

    D get(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);

}