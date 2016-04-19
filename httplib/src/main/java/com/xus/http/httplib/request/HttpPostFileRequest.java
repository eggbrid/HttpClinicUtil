package com.xus.http.httplib.request;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.xus.http.httplib.model.FormFile;
import com.xus.http.httplib.model.HttpPostRequestModel;
import com.xus.http.httplib.model.HttpResultModel;
import com.xus.http.httplib.other.HttpHelperConstant;

public class HttpPostFileRequest extends HttpRequestBase {
	private static int REQUEST_TIME_OUT = 60 * 1000;

	public void executePost(HttpPostRequestModel requestModel, int flag,
			Map<String, String> attributes) {

		String result = "";
		String PREFIX = "--";
		String LINEND = "\r\n";
		String CHARSET = "UTF-8";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String BOUNDARY = java.util.UUID.randomUUID().toString();

		try {
			URL url = new URL(requestModel.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setConnectTimeout(requestModel.getConnectionTimeout());
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
					+ ";boundary=" + BOUNDARY);
			// 首先组拼文本类型的参数
			StringBuilder sb = new StringBuilder();
			Log.d("hht", "请求地址：" + requestModel.getUrl());
			Log.d("hht", "请求：" + requestModel.getPostPrams().getFileString());
			Map<String, String> params = requestModel.getPostPrams()
					.getPostFilePrams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINEND);
				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"" + LINEND);
				sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
				sb.append(LINEND);
				sb.append(entry.getValue());
				sb.append(LINEND);
			}
			DataOutputStream outStream = new DataOutputStream(
					conn.getOutputStream());
			outStream.write(sb.toString().getBytes());
			FormFile formFile = requestModel.getPostPrams().getFormFile();
			if (formFile.inputStreamValueList != null) {
				Iterator iter = formFile.inputStreamValueList.entrySet()
						.iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String key = (String) entry.getKey();
					File f = (File) entry.getValue();
					InputStream in = null;
					if (f != null) {
						in = new FileInputStream(f);
					}
					formFile.addFormFile(key, f);

					/**
					 * 下面是传图
					 */
					StringBuilder sb1 = new StringBuilder();
					sb1.append(PREFIX);
					sb1.append(BOUNDARY);
					sb1.append(LINEND);
					sb1.append("Content-Disposition: form-data; name=\"" + key
							+ "\"; filename=\"" + f.getName() + LINEND);
					sb1.append("Content-Type: "+getType(f.getPath())+"; charset=" + CHARSET
							+ LINEND);
					sb1.append(LINEND);
					outStream.write(sb1.toString().getBytes());
					byte[] buffer = new byte[1024];
					int len = 0;
					InputStream inputStream = in;
					while ((len = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, len);
					}
					inputStream.close();
					outStream.write(LINEND.getBytes());
				}
			} else {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				sb1.append("Content-Disposition: form-data; name=\"" + "files"
						+ "\"; filename=\"" + "" + "\"" + LINEND);
				sb1.append("Content-Type: image/jpg; charset=" + CHARSET
						+ LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());
				outStream.write(LINEND.getBytes());
			}

			// 请求结束标志
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
			outStream.write(end_data);
			outStream.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				InputStream in = conn.getInputStream();
				InputStreamReader isReader = new InputStreamReader(in, CHARSET);
				BufferedReader bufReader = new BufferedReader(isReader);

				if (bufReader != null) {
					result = bufReader.readLine();
					if (result == null || result.equals("")) {
						setResultModel(new HttpResultModel(
								HttpHelperConstant.HTTP_BACK_ERROR, null,
								"返回数据为空", flag, requestModel.getClasss(),
								attributes));
						return;
					}
					Log.d("hht", "返回：" + result);
				} else {
					setResultModel(new HttpResultModel(
							HttpHelperConstant.HTTP_BACK_ERROR, null, "返回数据为空",
							flag, requestModel.getClasss(), attributes));
					return;
				}

			} else {
				setResultModel(new HttpResultModel(
						HttpHelperConstant.HTTP_BACK_ERROR, null, "请求错误，错误码："
								+ responseCode, flag, requestModel.getClasss(),
						attributes));
				return;
			}
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_BACK_SUCCESS, result, "ok", flag,
					requestModel.getClasss(), attributes));
			if (conn != null) {
				conn.disconnect();
			}
		} catch (SocketTimeoutException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_TIME_OUT, null, e.toString(), flag,
					requestModel.getClasss(), attributes));
		} catch (UnsupportedEncodingException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_ENCODING_ERROR, null, e.toString(),
					flag, requestModel.getClasss(), attributes));
		} catch (ClientProtocolException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_PROTOCOL_ERROR, null, e.toString(),
					flag, requestModel.getClasss(), attributes));
		} catch (IOException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_IO_ERROR, null, e.toString(), flag,
					requestModel.getClasss(), attributes));
		} catch (Exception e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_BACK_ERROR, null, e.toString(),
					flag, requestModel.getClasss(), attributes));
		} finally {

		}
	}

	private String getType(String path) {
		if (path.endsWith(".jpg") || path.endsWith(".JPG")) {
			return "image/jpeg";
		} else if (path.endsWith(".png") || path.endsWith(".PNG")) {
			return "image/png";

		} else if (path.endsWith(".mp3") || path.endsWith(".MP3")) {
			return "audio/mp3";
		} else {
			return "";
		}
	}
}
