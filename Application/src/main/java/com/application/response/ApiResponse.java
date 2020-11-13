/**
 * 
 */
package com.application.response;

import org.springframework.http.HttpStatus;

/**
 * @author Abhishek Amar
 *
 */
public class ApiResponse {
	private HttpStatus code;
	private String message;
	private Object data;
	private int limit;
	private int offSet;

	public ApiResponse(HttpStatus code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

}
