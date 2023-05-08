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

package com.timbercld.core.exception;


import com.timbercld.core.utils.MessageUtils;

/**
 * 自定义异常
 *
 *
 */
public class TimbercldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private int code;
	private String msg;

	public TimbercldException(int code) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public TimbercldException(int code, String... params) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public TimbercldException(int code, Throwable e) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public TimbercldException(int code, Throwable e, String... params) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public TimbercldException(String msg) {
		super(msg);
		this.code = ErrorCode.ERR_INTERNAL_SERVER;
		this.msg = msg;
	}

	public TimbercldException(String msg, Throwable e) {
		super(msg, e);
		this.code = ErrorCode.ERR_INTERNAL_SERVER;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}