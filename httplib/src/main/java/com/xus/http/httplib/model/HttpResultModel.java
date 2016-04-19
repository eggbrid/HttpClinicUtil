package com.xus.http.httplib.model;

import java.util.Map;

import com.xus.http.httplib.framework.base.BaseParser;

public class HttpResultModel<T> {

	private Class<T> classs;
	private int type;
	private String backJson;
	private String errorMessage;
	private int flag;
	private	Map<String,String> attributes;
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public HttpResultModel(int type, String backJson, String errorMessage,
			int flag, Class<T> classs,Map<String,String> attributes) {
		super();
		this.type = type;
		this.backJson = backJson;
		this.errorMessage = errorMessage;
		this.flag = flag;
		this.classs = classs;
		this.attributes=attributes;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBackJson() {
		return backJson;
	}

	public void setBackJson(String backJson) {
		this.backJson = backJson;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Class<T> getClasss() {
		return classs;
	}

	public void setClasss(Class<T> classs) {
		this.classs = classs;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
