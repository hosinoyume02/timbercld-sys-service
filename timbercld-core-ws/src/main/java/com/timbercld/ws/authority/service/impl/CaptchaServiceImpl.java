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

package com.timbercld.ws.authority.service.impl;

import com.google.code.kaptcha.Producer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.timbercld.ws.authority.service.CaptchaService;
import com.timbercld.core.redis.RedisKeys;
import com.timbercld.core.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 *
 *
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;
    @Value("${timbercld.redis.open: false}")
    private boolean open;
    /**
     * Local Cache  5分钟过期
     */
    Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();

    @Override
    public BufferedImage create(String uuid) {

        String code = producer.createText();

        setCache(uuid, code);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        String captcha = getCache(uuid);
        return code.equalsIgnoreCase(captcha);
    }

    private void setCache(String key, String value){
        if(open){
            key = RedisKeys.getCaptchaKey(key);
            redisUtils.set(key, value, 300);
        }else{
            localCache.put(key, value);
        }
    }

    private String getCache(String key){
        if(!open){
            String captcha = localCache.getIfPresent(key);
            if(captcha != null){
                localCache.invalidate(key);
            }
            return captcha;
        }
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String) redisUtils.get(key);
        if(captcha != null){
            redisUtils.delete(key);
        }
        return captcha;
    }
}