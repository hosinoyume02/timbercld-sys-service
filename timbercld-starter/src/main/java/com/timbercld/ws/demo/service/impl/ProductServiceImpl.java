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

package com.timbercld.ws.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.demo.dao.ProductDAO;
import com.timbercld.ws.demo.dto.ProductDTO;
import com.timbercld.ws.demo.entity.ProductEntity;
import com.timbercld.ws.demo.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 操作日志
 *
 *
 * @since 1.0.0
 */
@Service
public class ProductServiceImpl extends BasicServiceImpl<ProductDAO, ProductEntity> implements ProductService {

    @Override
    public PageData<ProductDTO> page(Map<String, Object> params) {
        IPage<ProductEntity> page = basicDao.selectPage(
            getPage(params, Constant.UPDATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, ProductDTO.class);
    }

    @Override
    public ProductDTO get(Long id) {
        return ConvertUtils.sourceToTarget(basicDao.selectById(id), ProductDTO.class);
    }

    @Override
    public void save(ProductDTO dto) {
        basicDao.insert(ConvertUtils.sourceToTarget(dto, ProductEntity.class));
    }


    private QueryWrapper<ProductEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper<>();
        String status = (String) params.get("status");
        String productionName = (String) params.get("productName");
        wrapper.like(StringUtils.isNoneBlank(productionName),"product_name",productionName);
        wrapper.eq(StringUtils.isNoneBlank(status), "status", status);
        return wrapper;
    }



}