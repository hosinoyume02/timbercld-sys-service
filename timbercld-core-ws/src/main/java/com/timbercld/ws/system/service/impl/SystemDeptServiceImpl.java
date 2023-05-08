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

package com.timbercld.ws.system.service.impl;

import com.timbercld.ws.authority.utils.AuthorityUtils;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.constant.Constant;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.core.utils.TreeUtils;
import com.timbercld.ws.system.dao.SystemDeptDAO;
import com.timbercld.ws.system.dto.SystemDeptDTO;
import com.timbercld.ws.system.entity.SystemDeptEntity;
import com.timbercld.ws.system.enums.SuperAdminEnum;
import com.timbercld.ws.system.enums.SuperSubSystemEnum;
import com.timbercld.ws.system.service.SystemDeptService;
import com.timbercld.ws.system.service.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SystemDeptServiceImpl extends BasicServiceImpl<SystemDeptDAO, SystemDeptEntity> implements SystemDeptService {
	@Autowired
	private SystemUserService systemUserService;

	@Override
	public List<SystemDeptDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
		LoginUserDTO user = AuthorityUtils.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()
				&& user.getSuperSubSystem() == SuperSubSystemEnum.NO.value()) {
			params.put("deptIdList", getSubDeptIdList(user.getDeptId()));
		}

		//子系统
		params.put(Constant.SUB_SYSTEM_ID, user.getSubSystemId());

		//查询部门列表
		List<SystemDeptEntity> entityList = basicDao.getList(params);

		List<SystemDeptDTO> dtoList = ConvertUtils.sourceToTarget(entityList, SystemDeptDTO.class);

		return TreeUtils.build(dtoList);
	}

	@Override
	public SystemDeptDTO get(Long id) {
		//超级管理员，部门ID为null
		if(null == id){
			return null;
		}

		SystemDeptEntity entity = basicDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SystemDeptDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SystemDeptDTO dto) {
		SystemDeptEntity entity = ConvertUtils.sourceToTarget(dto, SystemDeptEntity.class);

		entity.setPids(getPidList(entity.getPid()));
		insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SystemDeptDTO dto) {
		SystemDeptEntity entity = ConvertUtils.sourceToTarget(dto, SystemDeptEntity.class);

		//上级部门不能为自身
		if(entity.getId().equals(entity.getPid())){
			throw new TimbercldException(ErrorCode.ERR_SUPERIOR_DEPT);
		}

		entity.setPids(getPidList(entity.getPid()));
		updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		//判断是否有子部门
		List<Long> subList = getSubDeptIdList(id);
		if(subList.size() > 1){
			throw new TimbercldException(ErrorCode.ERR_DEPT_SUB_DELETE);
		}

		//判断部门下面是否有用户
		int count = systemUserService.getCountByDeptId(id);
		if(count > 0){
			throw new TimbercldException(ErrorCode.ERR_DEPT_USER_DELETE);
		}

		//删除
		basicDao.deleteById(id);
	}

	@Override
	public List<Long> getSubDeptIdList(Long id) {
		List<Long> deptIdList = basicDao.getSubDeptIdList("%" + id + "%");
		deptIdList.add(id);

		return deptIdList;
	}

	/**
	 * 获取所有上级部门ID
	 * @param pid 上级ID
	 */
	private String getPidList(Long pid){
		//顶级部门，无上级部门
		if(Constant.DEPT_ROOT.equals(pid)){
			return Constant.DEPT_ROOT + "";
		}

		//所有部门的id、pid列表
		List<SystemDeptEntity> deptList = basicDao.getIdAndPidList();

		//list转map
		Map<Long, SystemDeptEntity> map = new HashMap<>(deptList.size());
		for(SystemDeptEntity entity : deptList){
			map.put(entity.getId(), entity);
		}

		//递归查询所有上级部门ID列表
		List<Long> pidList = new ArrayList<>();
		getPidTree(pid, map, pidList);

		return StringUtils.join(pidList, ",");
	}

	private void getPidTree(Long pid, Map<Long, SystemDeptEntity> map, List<Long> pidList) {
		//顶级部门，无上级部门
		if(Constant.DEPT_ROOT.equals(pid)){
			return ;
		}

		//上级部门存在
		SystemDeptEntity parent = map.get(pid);
		if(parent != null){
			getPidTree(parent.getPid(), map, pidList);
		}

		pidList.add(pid);
	}
}
