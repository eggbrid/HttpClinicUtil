package com.xus.http.httplib.https;

import java.util.Map;

import com.xus.http.httplib.interfaces.HttpsCallBack;
import com.xus.http.httplib.model.HttpPostRequestModel;
import com.xus.http.httplib.model.HttpPutRequestModel;
import com.xus.http.httplib.request.HttpPostRequest;
import com.xus.http.httplib.request.HttpPutRequest;

public class HttpPutThread implements Runnable {
	private HttpPutRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private Map<String,String> attributes;
	private int flag;
	public HttpPutThread(HttpPutRequestModel requestModel ,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack=httpsCallBack;
		this.flag=flag;
		this.attributes=attributes;
	}
	@Override
	public void run() {
		HttpPutRequest postRequest = new HttpPutRequest();
		postRequest.executePost(httpRequestModel, flag,attributes);
		httpsCallBack.back(postRequest.getResultModel());
	}
	
}
