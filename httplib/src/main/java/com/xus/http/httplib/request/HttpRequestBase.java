package com.xus.http.httplib.request;

import com.xus.http.httplib.model.HttpResultModel;

public class HttpRequestBase {
	private HttpResultModel resultModel;

	public HttpResultModel getResultModel() {
		return resultModel;
	}

	public void setResultModel(HttpResultModel resultModel) {
		this.resultModel = resultModel;
	}
}
