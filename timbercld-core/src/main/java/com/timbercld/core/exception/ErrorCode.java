package com.timbercld.core.exception;
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



public interface ErrorCode {
    int ERR_INTERNAL_SERVER = 500;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    /*
     * common error
     * */
    int NOT_NULL = 10001;
    int DB_RECORD_EXISTS = 10002;
    int ERR_PARAMS_GET = 10003;
    int INVALID_SYMBOL = 10004;
    int ERR_REDIS = 10005;
    int ERR_JSON_FORMAT = 10006;
    int ERR_SQL_PARAMS = 10007;
    /*
    * account error
    * */
    int ERR_ACCOUNT_PASSWORD = 11001;
    int ACCOUNT_DISABLE = 11002;
    int ERR_CAPTCHA = 11003;
    int ERR_PASSWORD = 11004;
    int IDENTIFIER_NOT_NULL = 11005;
    int ACCOUNT_NOT_EXIST = 11006;
    int ERR_SUPERIOR_DEPT = 11007;
    int ERR_SUPERIOR_MENU = 11008;
    int ERR_DATA_SCOPE_PARAMS = 11009;
    int ERR_DEPT_SUB_DELETE = 11010;
    int ERR_DEPT_USER_DELETE = 11011;
    int SUB_MENU_EXIST = 11012;
    int TOKEN_NOT_EMPTY = 11013;
    int ACCOUNT_LOCK = 11014;
    int ACCOUNT_EXIST = 11015;
    int TOKEN_INVALID = 11016;
    /*
    * storage
    * */
    int UPLOAD_FILE_EMPTY = 12001;
    int ERR_STORAGE_UPLOAD_FILE = 12002;
    /*
    * message
    * */
    int SEND_SMS_ERROR = 13001;
    int MAIL_TEMPLATE_NOT_EXISTS = 13002;
    int ERR_SMS_CONFIG = 13003;
    /*
    * scheduler
    * */
    int ERR_SCHEDULER = 14001;





}
