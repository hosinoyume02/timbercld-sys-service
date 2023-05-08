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

package com.timbercld.ws.subsys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.timbercld.ws.authority.password.PasswordUtils;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.page.PageData;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.subsys.dao.SubSystemDAO;
import com.timbercld.ws.subsys.dto.SubSystemDTO;
import com.timbercld.ws.subsys.entity.SubSystemEntity;
import com.timbercld.ws.subsys.service.SubSystemService;
import com.timbercld.ws.system.dao.SystemUserDAO;
import com.timbercld.ws.system.entity.SystemUserEntity;
import com.timbercld.ws.system.enums.DeleteEnum;
import com.timbercld.ws.system.enums.SubSystemEnum;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import com.timbercld.ws.system.enums.SuperSubSystemEnum;
import com.timbercld.ws.system.service.SystemRoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class SubSystemServiceImpl extends BasicServiceImpl<SubSystemDAO, SubSystemEntity> implements SubSystemService {
	@Autowired
	private SystemRoleUserService systemRoleUserService;
	@Resource
	private SystemUserDAO systemUserDao;

	@Override
	public PageData<SubSystemDTO> page(Map<String, Object> params) {
		//转换成like
		paramsToLike(params, "SubSystemName");

		//分页
		IPage<SubSystemEntity> page = getPage(params, Constant.CREATE_DATE, false);

		//查询
		List<SubSystemEntity> list = basicDao.getList(params);

		return getPageData(list, page.getTotal(), SubSystemDTO.class);
	}

	@Override
	public SubSystemDTO get(Long id) {
		SubSystemEntity entity = basicDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SubSystemDTO.class);
	}



	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SubSystemDTO dto) {
		SubSystemEntity entity = ConvertUtils.sourceToTarget(dto, SubSystemEntity.class);

		//账号已存在
		SystemUserEntity userEntity = systemUserDao.getByUsername(dto.getUsername());
		if(userEntity != null){
			throw new TimbercldException(ErrorCode.ACCOUNT_EXIST);
		}

		//保存用户
		userEntity = ConvertUtils.sourceToTarget(dto, SystemUserEntity.class);
		userEntity.setPassword(PasswordUtils.encode(dto.getPassword()));
		userEntity.setSuperSubSystem(SuperSubSystemEnum.YES.value());
		userEntity.setSuperAdmin(SuperAdminEnum.NO.value());
		userEntity.setGender(2);
		systemUserDao.insert(userEntity);

		//保存角色用户关系
		systemRoleUserService.saveOrUpdate(userEntity.getId(), dto.getRoleIdList());

		//保存子系统
		entity.setUserId(userEntity.getId());
		entity.setDelFlag(DeleteEnum.NO.value());
		entity.setSubSystem(SubSystemEnum.NO.value());
		insert(entity);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SubSystemDTO dto) {
		//更新子系统
		SubSystemEntity entity = ConvertUtils.sourceToTarget(dto, SubSystemEntity.class);
		updateById(entity);

		//查询子系统用户ID
		Long userId = basicDao.selectById(entity.getId()).getUserId();

		//更新用户
		SystemUserEntity userEntity = ConvertUtils.sourceToTarget(dto, SystemUserEntity.class);
		userEntity.setId(userId);
		//密码加密
		if(StringUtils.isBlank(dto.getPassword())){
			userEntity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(dto.getPassword());
			userEntity.setPassword(password);
		}
		systemUserDao.updateById(userEntity);

		//更新角色用户关系
		systemRoleUserService.saveOrUpdate(userEntity.getId(), dto.getRoleIdList());
	}

	@Override
	public void delete(Long[] ids) {
		basicDao.deleteBatch(ids);
	}

	@Override
	public SubSystemDTO getSubSystemId(Long subSystemId) {
		SubSystemEntity entity = basicDao.getSubSystemId(subSystemId);

		return ConvertUtils.sourceToTarget(entity, SubSystemDTO.class);
	}
}
