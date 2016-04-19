package com.xus.http.httplib.model;

public class HttpDelectRequestModel extends HttpRequestModel {
	private DelectParams delectParams;
	public <T> HttpDelectRequestModel(String url, int connectionTimeout, int soTimeout,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);

	}

	public <T> HttpDelectRequestModel(String url, int connectionTimeout,
			int soTimeout, DelectParams delectParams,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);
		this.delectParams=delectParams;

	}

	public DelectParams getDelectParams() {
		return delectParams==null?new DelectParams():delectParams;
	}

	public void setDelectParams(DelectParams delectParams) {
		this.delectParams = delectParams;
	}
	
}
