package com.xus.http.httplib.model;

public class HttpGetRequestModel extends HttpRequestModel {
	private GetParams getPrams;
	public <T> HttpGetRequestModel(String url, int connectionTimeout, int soTimeout,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);

	}

	public <T> HttpGetRequestModel(String url, int connectionTimeout,
			int soTimeout, GetParams getPrams,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);
		this.getPrams=getPrams;

	}

	public GetParams getGetPrams() {
		return getPrams==null?new GetParams():getPrams;
	}

	public void setGetPrams(GetParams getPrams) {
		this.getPrams = getPrams;
	}
	
}
