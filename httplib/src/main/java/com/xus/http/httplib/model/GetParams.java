package com.xus.http.httplib.model;

import java.util.Iterator;

public class GetParams extends BaseParams {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5041793410763948512L;

	public void addParams(String key, Object object) {
		put(key, object);
	}

	@Override
	public String toString() {
		int i = 0;
		String s = "";
		Iterator iter = entrySet().iterator();
		while (iter.hasNext()) {
			if (i != 0) {
				s = s + "&";
			}
			Entry  entry =(java.util.Map.Entry) iter.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			s = s + key + "=" +val;
			i++;
		}

		return s;
	}

}
