package com.xus.http.httplib.https;

import java.util.Map;

import com.xus.http.httplib.interfaces.HttpsCallBack;
import com.xus.http.httplib.model.HttpPostRequestModel;
import com.xus.http.httplib.request.HttpPostFileRequest;
import com.xus.http.httplib.request.HttpPostRequest;

public class HttpPostThread implements Runnable {
	private HttpPostRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private int flag;
	private Map<String,String> attributes;
	public HttpPostThread(HttpPostRequestModel requestModel ,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack=httpsCallBack;
		this.flag=flag;
		this.attributes=attributes;
	}
	@Override
	public void run() {
		if (httpRequestModel.getPostPrams().isHasFile()) {
			HttpPostFileRequest postRequest = new HttpPostFileRequest();
			postRequest.executePost(httpRequestModel, flag,attributes);
			httpsCallBack.back(postRequest.getResultModel());
		}else{
			HttpPostRequest postRequest = new HttpPostRequest();
			postRequest.executePost(httpRequestModel, flag,attributes);
			httpsCallBack.back(postRequest.getResultModel());
		}

	}
	
}
