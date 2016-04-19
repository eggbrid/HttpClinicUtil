package com.xus.http.httplib.model;


public class HttpRequestModel<T> {
	private Class<T> classs;
	private String url;
	private int connectionTimeout;
	private int soTimeout;

	public HttpRequestModel(String url, int connectionTimeout, int soTimeout,Class<T> classs) {
		this.url = url;
		this.connectionTimeout = connectionTimeout;
		this.soTimeout = soTimeout;
		this.classs=classs;
	}


	public Class<T> getClasss() {
		return classs;
	}


	public void setClasss(Class<T> classs) {
		this.classs = classs;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

}
