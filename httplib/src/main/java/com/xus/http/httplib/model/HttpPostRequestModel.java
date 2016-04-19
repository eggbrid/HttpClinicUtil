package com.xus.http.httplib.model;

public class HttpPostRequestModel extends HttpRequestModel {
	private PostPrams postPrams;
	public <T> HttpPostRequestModel(String url, int connectionTimeout, int soTimeout,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);

	}

	public <T> HttpPostRequestModel(String url, int connectionTimeout,
			int soTimeout, PostPrams postPrams,Class<T> classs) {
		super(url, connectionTimeout, soTimeout,classs);
		this.postPrams=postPrams;

	}

	public PostPrams getPostPrams() {
		return postPrams==null?new PostPrams():postPrams;
	}

	public void setPostPrams(PostPrams postPrams) {
		this.postPrams = postPrams;
	}
	
}
