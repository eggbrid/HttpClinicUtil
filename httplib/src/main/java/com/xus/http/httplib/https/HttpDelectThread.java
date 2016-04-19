package com.xus.http.httplib.https;

import java.util.Map;

import com.xus.http.httplib.interfaces.HttpsCallBack;
import com.xus.http.httplib.model.HttpDelectRequestModel;
import com.xus.http.httplib.request.HttpDelectRequest;

public class HttpDelectThread implements Runnable {
	private HttpDelectRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private int flag;
	private Map<String, String> attributes;

	public HttpDelectThread(HttpDelectRequestModel requestModel,
			HttpsCallBack httpsCallBack, int flag,
			Map<String, String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack = httpsCallBack;
		this.flag = flag;
	}

	@Override
	public void run() {
		HttpDelectRequest getRequest = new HttpDelectRequest();
		getRequest.executeGet(httpRequestModel, flag,attributes);
		httpsCallBack.back(getRequest.getResultModel());
	}

}
