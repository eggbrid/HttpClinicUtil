package com.xus.http.httplib.https;

import java.util.Map;

import android.os.Handler;
import android.util.Log;

import com.xus.http.httplib.framework.base.BaseParser;
import com.xus.http.httplib.interfaces.HttpUtilBack;
import com.xus.http.httplib.interfaces.HttpsCallBack;
import com.xus.http.httplib.model.DelectParams;
import com.xus.http.httplib.model.GetParams;
import com.xus.http.httplib.model.HttpDelectRequestModel;
import com.xus.http.httplib.model.HttpGetRequestModel;
import com.xus.http.httplib.model.HttpPostRequestModel;
import com.xus.http.httplib.model.HttpPutRequestModel;
import com.xus.http.httplib.model.HttpResultModel;
import com.xus.http.httplib.model.PostPrams;
import com.xus.http.httplib.model.PutPrams;
import com.xus.http.httplib.other.HttpHelperConstant;

public class HttpUtil implements HttpsCallBack {
	private Handler handler = new Handler();

	public static boolean isTypeOf(String type, int flag) {
		return type.equals(flag);
	}

	public HttpUtil(HttpUtilBack back) {
		this.back = back;
	}

	private HttpUtilBack back;


/**
 * 发起Post请求 *
 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
 * @param url 请求url
 * @param postPrams 请求参数
 * @param attributes 请求属性 
 */
	public <T> void doPost(int flag, String url, PostPrams postPrams,Map<String,String> attributes) {
		doPost(flag, url, 60000, postPrams,null,attributes);
	}
	/**
	 * 发起Post请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param <T>
	 * 
	 * @param url
	 *            请求url
	 * @param postPrams
	 *            请求参数
	 */
	public <T> void doPost(int flag, String url, PostPrams postPrams,Class<T> calsss,Map<String,String> attributes) {
		doPost(flag, url, 60000, postPrams,calsss,attributes);
	}

	/**
	 * 发起Post请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param <T>
	 * 
	 * @param url
	 *            请求url
	 * @param connectionTimeout
	 *            链接请求超时时间 毫秒 默认请求超时为10秒
	 */
	public <T> void doPost(int flag, String url, int connectionTimeout,
			PostPrams postPrams,Class<T> calsss,Map<String,String> attributes) {
		doPost(flag, url, connectionTimeout, 60000, postPrams,calsss,attributes);
	}

	public <T> void doPost(int flag, String url, int connectionTimeout,
			int soTimeout, PostPrams postPrams,Class<T> calsss,Map<String,String> attributes) {
		if (calsss==null) {
			calsss=(Class<T>) BaseParser.class;
		}
		HttpPostRequestModel model = new HttpPostRequestModel(url,
				connectionTimeout, soTimeout, postPrams,calsss);
		HttpService.doPost(model, this, flag,attributes);
	}
	/**
	 * 发起get请求
	 * @param <T>
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求url 默认连接超时为5秒，请求超时为10秒
	 */
	public <T> void doGet(int flag, String url) {
		doGet(flag, url, 60000,null,null,null);
	}
	/**
	 * 发起get请求
	 * @param <T>
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求url 默认连接超时为5秒，请求超时为10秒
	 */
	public <T> void doGet(int flag, String url,GetParams getParams,Class<T> calsss,Map<String,String> attributes) {
		doGet(flag, url, 60000,getParams,calsss,attributes);
	}

	/**
	 * 发起get请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param <T>
	 * 
	 * @param url
	 *            请求url
	 * @param connectionTimeout
	 *            链接请求超时时间 毫秒 默认请求超时为10秒
	 */
	public <T> void doGet(int flag, String url, int connectionTimeout,GetParams getParams,Class<T> calsss,Map<String,String> attributes) {
		doGet(flag, url, connectionTimeout, 60000,getParams,calsss,attributes);
	}



	/**
	 * * 发起Get请求
	 * @param <T>
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求链接
	 * @param connectionTimeout
	 *            链接超时 毫秒
	 * @param soTimeout
	 *            请求超时 毫秒
	 * @param getParams
	 *            请求参数
	 */
	public <T> void doGet(int flag, String url, int connectionTimeout,
			int soTimeout, GetParams getParams,Class<T> calsss,Map<String,String> attributes) {
		if (calsss==null) {
			calsss=(Class<T>) BaseParser.class;
		}
		if (getParams==null) {
			getParams=new GetParams();
		}
		HttpGetRequestModel model = new HttpGetRequestModel(url,
				connectionTimeout, soTimeout, getParams,calsss);
		HttpService.doGet(model, this, flag,attributes);
	}
	/**
	 * 发起Dele请求
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求url 默认连接超时为5秒，请求超时为10秒
	 */
	public void doDele(int flag, String url) {
		doDele(flag, url, 60000,null,null,null);
	}

	/**
	 * 发起Dele请求
	 * @param <T>
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求url 默认连接超时为5秒，请求超时为10秒
	 */
	public <T> void doDele(int flag, String url, DelectParams delectParams,Class<T> calsss,Map<String,String> attributes) {
		doDele(flag, url, 60000,delectParams,calsss,attributes);
	}

	/**
	 * 发起get请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param <T>
	 * 
	 * @param url
	 *            请求url
	 * @param connectionTimeout
	 *            链接请求超时时间 毫秒 默认请求超时为10秒
	 */
	public <T> void doDele(int flag, String url, int connectionTimeout, DelectParams delectParams,Class<T> calsss,Map<String,String> attributes) {
		doDele(flag, url, connectionTimeout, 60000,delectParams,calsss,attributes);
	}



	/**
	 * * 发起Dele请求
	 * @param <T>
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求链接
	 * @param connectionTimeout
	 *            链接超时 毫秒
	 * @param soTimeout
	 *            请求超时 毫秒

	 */
	public <T> void doDele(int flag, String url, int connectionTimeout,
			int soTimeout, DelectParams delectParams,Class<T> calsss,Map<String,String> attributes) {
		if (calsss==null) {
			calsss=(Class<T>) BaseParser.class;
		}
		if (delectParams==null) {
			delectParams=new DelectParams();
		}
		HttpDelectRequestModel model = new HttpDelectRequestModel(url,
				connectionTimeout, soTimeout, delectParams,calsss);
		HttpService.doDelect(model, this, flag,attributes);
	}
	/**
	 * 发起Put请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * 
	 * @param url
	 *            请求url

	 */
	public void doPut(int flag, String url, PutPrams putPrams,Map<String,String> attributes) {
		doPut(flag, url, 60000, putPrams,null,attributes);
		}

	/**
	 * 发起Put请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param <T>
	 * 
	 * @param url
	 *            请求url

	 */
	public <T> void doPut(int flag, String url, PutPrams putPrams,Class<T> calsss,Map<String,String> attributes) {
		doPut(flag, url, 60000, putPrams,calsss,attributes);
	}

	/**
	 * 发起Put请求 * @param flag 此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param <T>
	 * 
	 * @param url
	 *            请求url
	 * @param connectionTimeout
	 *            链接请求超时时间 毫秒 默认请求超时为10秒
	 */
	public <T> void doPut(int flag, String url, int connectionTimeout,
			PutPrams putPrams,Class<T> calsss,Map<String,String> attributes) {
		doPut(flag, url, connectionTimeout, 60000, putPrams,calsss,attributes);
	}

	/**
	 * * 发起Put请求
	 * @param <T>
	 * 
	 * @param flag
	 *            此次请求的标识，用于区分同一个方法中的多次不同请求
	 * @param url
	 *            请求链接
	 * @param connectionTimeout
	 *            链接超时 毫秒
	 * @param soTimeout
	 *            请求超时 毫秒
	 */
	public <T> void doPut(int flag, String url, int connectionTimeout,
			int soTimeout, PutPrams putPrams,Class<T> calsss,Map<String,String> attributes) {
		if (calsss==null) {
			calsss=(Class<T>) BaseParser.class;

		}
		HttpPutRequestModel model = new HttpPutRequestModel(url,
				connectionTimeout, soTimeout, putPrams,calsss);
		HttpService.doPut(model, this, flag,attributes);
	}

	@Override
	public void back(final HttpResultModel model) {
		handler.post(new Runnable() {

			@Override
			public void run() {
				switch (model.getType()) {
				case HttpHelperConstant.HTTP_BACK_SUCCESS:
					back.onSuccess(model.getFlag(), model.getBackJson(),model.getClasss(),model.getAttributes());
					Log.v("HttpUtil", model.getType() + "http is ok");
					break;
				case HttpHelperConstant.HTTP_BACK_ERROR:
				case HttpHelperConstant.HTTP_ENCODING_ERROR:
				case HttpHelperConstant.HTTP_IO_ERROR:
				case HttpHelperConstant.HTTP_PROTOCOL_ERROR:
				case HttpHelperConstant.HTTP_TIME_OUT:
					back.onfail(model.getFlag(), model.getType(),model.getAttributes());
					Log.e("HttpUtil", model.getType() + "error message:"
							+ model.getErrorMessage());
					break;
				default:
					break;
				}
			}
		});

	}

}
