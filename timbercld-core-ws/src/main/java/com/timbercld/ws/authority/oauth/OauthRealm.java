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

package com.timbercld.ws.authority.oauth;

import com.timbercld.ws.authority.entity.TokenEntity;
import com.timbercld.ws.authority.service.PermissionService;
import com.timbercld.ws.authority.dto.LoginUserDTO;
import com.timbercld.core.exception.ErrorCode;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.core.utils.MessageUtils;
import com.timbercld.ws.system.entity.SystemUserEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Component
public class OauthRealm extends AuthorizingRealm {
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OauthToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LoginUserDTO user = (LoginUserDTO)principals.getPrimaryPrincipal();

        Set<String> permsSet = permissionService.getUserPermissions(user);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permsSet);
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        TokenEntity tokenEntity = permissionService.getByToken(accessToken);
        //token失效
        if(null == tokenEntity || tokenEntity.getExpireDate().getTime() < new Date().getTime()){
            throw new IncorrectCredentialsException(MessageUtils.getMessage(ErrorCode.TOKEN_INVALID));
        }


        SystemUserEntity userEntity = permissionService.getUser(tokenEntity.getUserId());


        LoginUserDTO loginUserDTO = ConvertUtils.sourceToTarget(userEntity, LoginUserDTO.class);


        List<Long> deptIdList = permissionService.getDataScopeList(loginUserDTO.getId());
        loginUserDTO.setDeptIdList(deptIdList);
        if(loginUserDTO.getStatus() == 0){
            throw new LockedAccountException(MessageUtils.getMessage(ErrorCode.ACCOUNT_LOCK));
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUserDTO, accessToken, getName());
        return info;
    }

}