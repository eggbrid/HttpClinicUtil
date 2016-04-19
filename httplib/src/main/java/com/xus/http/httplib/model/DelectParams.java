package com.xus.http.httplib.model;
public class DelectParams extends BaseParams {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5041793410763948512L;

	public void addParams(String key, Object object) {
		put(key, object);
	}

	@Override
	public String toString() {
		int i=0;
		String s="?";
		while (entrySet().iterator().hasNext()) {
		Entry<String, Object> entry =  entrySet().iterator().next();
		Object key = entry.getKey();
		Object val = entry.getValue();
		s=s+key+"="+val.toString();
		if (i!=0) {
			s=s+"&";
		}
		}
		return s;
	}

}
