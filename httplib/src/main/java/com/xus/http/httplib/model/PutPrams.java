package com.xus.http.httplib.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

public class PutPrams extends BaseParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1408567938395392105L;
	private List<BasicNameValuePair> putPrams=new ArrayList<BasicNameValuePair>();
	public List<BasicNameValuePair> getPutPrams() {
		return putPrams;
	}
	public void setPutPrams(List<BasicNameValuePair> putPrams) {
		this.putPrams = putPrams;
	}
	
	public void addPrams(String key,String prm){
		BasicNameValuePair basicNameValuePair=new BasicNameValuePair(key, prm);
		putPrams.add(basicNameValuePair);
	}
}
