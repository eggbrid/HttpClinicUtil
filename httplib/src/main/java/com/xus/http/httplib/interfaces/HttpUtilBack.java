package com.xus.http.httplib.interfaces;

import java.util.Map;


public interface HttpUtilBack {
<T> void onSuccess(int type, String json, Class<T> classs, Map<String, String> attributes);
void onfail(int type, int errorType, Map<String, String> attributes);
}
