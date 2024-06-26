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

package com.timbercld.ws.demo.service;

import com.timbercld.core.page.PageData;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.demo.dto.ProductDTO;
import com.timbercld.ws.demo.entity.ProductEntity;

import java.util.Map;


public interface ProductService extends BasicService<ProductEntity> {
    /**
     * fetch page data by params
     *
     * @param params search criteria
     * @return PageData<ProductDTO>
     */
    PageData<ProductDTO> page(Map<String, Object> params);
    /**
     * fetch dto by id
     *
     * @param id id
     * @return ProductDTO
     */
    ProductDTO get(Long id);
    /**
     * save entity by dto
     *
     * @param dto productDTO
     */
    void save(ProductDTO dto);
}