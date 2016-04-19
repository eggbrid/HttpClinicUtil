package com.xus.http.httplib.model;

public class HttpPutRequestModel extends HttpRequestModel {
	private PutPrams putPrams;
	public <T> HttpPutRequestModel(String url, int connectionTimeout, int soTimeout,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);

	}

	public <T> HttpPutRequestModel(String url, int connectionTimeout,
			int soTimeout, PutPrams putPrams,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);
		this.putPrams=putPrams;

	}

	public PutPrams getPutPrams() {
		return putPrams==null?new PutPrams():putPrams;
	}

	public void setPutPrams(PutPrams putPrams) {
		this.putPrams = putPrams;
	}
	
}
