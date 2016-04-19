package com.xus.http.httplib.model;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostPrams extends BaseParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331511632911431744L;
	private List<BasicNameValuePair> postPrams = new ArrayList<BasicNameValuePair>();
	protected boolean hasFile = false;
	private Map<String, String> postFilePrams = new HashMap<String, String>();
	private FormFile formFile=new FormFile();

	public boolean isHasFile() {
		return hasFile;
	}

	public void setHasFile(boolean hasFile) {
		this.hasFile = hasFile;
	}

	public List<BasicNameValuePair> getPostPrams() {
		return postPrams;
	}

	public void setPostPrams(List<BasicNameValuePair> postPrams) {
		this.postPrams = postPrams;
	}

	public void addPrams(String key, String prm) {
		BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, prm);
		postPrams.add(basicNameValuePair);
	}

	public void addFilePrams(String key, String prm) {
		hasFile = true;
		postFilePrams.put(key, prm);
	}

	public void addFile(String key, File f) {
		hasFile = true;
		try {
			InputStream in = null;
			if (f != null) {
				in = new FileInputStream(f);
			}
			formFile.addFormFile(key, f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getPostFilePrams() {
		return postFilePrams;
	}

	public void setPostFilePrams(Map<String, String> postFilePrams) {
		this.postFilePrams = postFilePrams;
	}

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	public String getFileString(){

		int i = 0;
		String s = "";
		Iterator iter = postFilePrams.entrySet().iterator();
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
	public String getString(){
		String s = "{";
		for (int i = 0; i < postPrams.size(); i++) {
			s=s+postPrams.get(i).getName()+":"+postPrams.get(i).getValue();
		}
		s=s+"}";
		return s;
	}
}
