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

package com.timbercld.ws.demo.controller;

import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.core.validator.group.UpdateGroup;
import com.timbercld.ws.demo.entity.ProductEntity;
import com.timbercld.ws.logger.aspect.LogOperation;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.page.PageData;

import com.timbercld.core.utils.LocalUploadUtils;
import com.timbercld.core.utils.Result;
import com.timbercld.core.validator.AssertUtils;
import com.timbercld.core.validator.ValidatorUtils;
import com.timbercld.core.validator.group.DefaultGroup;
import com.timbercld.core.validator.group.SaveGroup;
import com.timbercld.ws.demo.dto.ProductDTO;

import com.timbercld.ws.demo.service.ProductService;

import com.timbercld.ws.scheduler.dto.SchedulerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


@RestController
@RequestMapping("demo/product")
@Api(tags="示例")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private LocalUploadUtils uploadUtils;
    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataTypeClass = Integer.class) ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataTypeClass = Integer.class) ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataTypeClass = String.class) ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataTypeClass = String.class) ,
        @ApiImplicitParam(name = "status", value = "状态  0：上架    1：下架", paramType = "query", dataTypeClass = Integer.class)
    })
    @RequiresPermissions("demo:product:page")
    public Result<PageData<ProductDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<ProductDTO> page = productService.page(params);

        return new Result<PageData<ProductDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:product:info")
    public Result<ProductDTO> get(@PathVariable("id") Long id){
        ProductDTO data = productService.get(id);

        return new Result<ProductDTO>().ok(data);
    }
    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:product:save")
    public Result save(@RequestBody ProductDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, SaveGroup.class, DefaultGroup.class);

        productService.save(dto);

        return new Result();
    }
    @DeleteMapping("{id}")
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:product:delete")
    public Result delete(@PathVariable("id") Long id){
        //效验数据
        AssertUtils.isNull(id, "id");

        productService.deleteById(id);

        return new Result();
    }
    @PostMapping("/upload/")
    @ApiOperation("图片上传")
    @LogOperation("图片上传")
    @RequiresPermissions("demo:product:save")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("fileName") String fileName){
        String uploadName = "";

        if (file.isEmpty()) {
            return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }

        if (fileName.isEmpty() || fileName.equals("")) {
            uploadName = file.getOriginalFilename();
        }else {
            //上传文件
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            uploadName = fileName + "." + extension;
        }
        String url = this.uploadUtils.saveLocal(file,"demo",uploadName);
        return new Result().ok(url);
    }
    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:product:update")
    public Result update(@RequestBody ProductDTO dto){
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        productService.updateById(ConvertUtils.sourceToTarget(dto, ProductEntity.class));

        return new Result();
    }
}