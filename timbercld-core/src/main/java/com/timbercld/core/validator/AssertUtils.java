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

package com.timbercld.core.validator;

import com.timbercld.core.exception.TimbercldException;
import com.timbercld.core.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


public class AssertUtils {

    public static void isBlank(String str, String... params) {
        isBlank(str, ErrorCode.NOT_NULL, params);
    }

    public static void isBlank(String str, Integer code, String... params) {
        if(null == code){
            throw new TimbercldException(ErrorCode.NOT_NULL, "code");
        }

        if (!StringUtils.isNoneBlank(str)) {
            throw new TimbercldException(code, params);
        }
    }

    public static void isNull(Object object, String... params) {
        isNull(object, ErrorCode.NOT_NULL, params);
    }

    public static void isNull(Object object, Integer code, String... params) {
        if(null == code){
            throw new TimbercldException(ErrorCode.NOT_NULL, "code");
        }

        if (null == object) {
            throw new TimbercldException(code, params);
        }
    }

    public static void isArrayEmpty(Object[] array, String... params) {
        isArrayEmpty(array, ErrorCode.NOT_NULL, params);
    }

    public static void isArrayEmpty(Object[] array, Integer code, String... params) {
        if(null == code){
            throw new TimbercldException(ErrorCode.NOT_NULL, "code");
        }
        if(null == array || array.length <= 0){
            throw new TimbercldException(code, params);
        }
    }

    public static void isListEmpty(List<?> list, String... params) {
        isListEmpty(list, ErrorCode.NOT_NULL, params);
    }

    public static void isListEmpty(List<?> list, Integer code, String... params) {
        if(null == code){
            throw new TimbercldException(ErrorCode.NOT_NULL, "code");
        }
        if(null == list || list.size() <= 0){
            throw new TimbercldException(code, params);
        }
    }

    public static void isMapEmpty(Map map, String... params) {
        isMapEmpty(map, ErrorCode.NOT_NULL, params);
    }

    public static void isMapEmpty(Map map, Integer code, String... params) {
        if(null == code){
            throw new TimbercldException(ErrorCode.NOT_NULL, "code");
        }
        if(null == map || map.isEmpty()){
            throw new TimbercldException(code, params);
        }
    }
}