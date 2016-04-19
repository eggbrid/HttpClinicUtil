package com.xus.http.httplib.https;

import java.util.Map;

import com.xus.http.httplib.interfaces.HttpsCallBack;
import com.xus.http.httplib.model.HttpGetRequestModel;
import com.xus.http.httplib.request.HttpGetRequest;

public class HttpGetThread implements Runnable {
	private HttpGetRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private int flag;
	private Map<String, String> attributes;

	public HttpGetThread(HttpGetRequestModel requestModel ,HttpsCallBack httpsCallBack,int flag,Map<String, String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack=httpsCallBack;
		this.flag=flag;
		this.attributes=attributes;
	}

	@Override
	public void run() {
		HttpGetRequest getRequest = new HttpGetRequest();
			getRequest.executeGet(httpRequestModel,flag,attributes);
			httpsCallBack.back(getRequest.getResultModel());
	}


	
}
